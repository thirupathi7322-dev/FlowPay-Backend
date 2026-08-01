package com.flowpay.backend.config;

import com.flowpay.backend.service.JwtService;
import com.flowpay.backend.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;
import com.flowpay.backend.entity.User;

import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtService jwtService,
                                   UserRepository userRepository) {

        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }
        String jwt = authHeader.substring(7);
        String email = jwtService.extractEmail(jwt);
        User user = userRepository.findByEmail(email)
                .orElse(null);
        if (user != null && jwtService.isTokenValid(jwt, user)) {
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            null
                    );

            authentication.setDetails(
                    new WebAuthenticationDetailsSource()
                            .buildDetails(request)
            );

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

        }
        filterChain.doFilter(request, response);

    }

}
