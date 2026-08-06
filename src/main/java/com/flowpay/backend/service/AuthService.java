package com.flowpay.backend.service;

import com.flowpay.backend.dto.LoginRequest;
import com.flowpay.backend.dto.LoginResponse;
import com.flowpay.backend.entity.User;
import com.flowpay.backend.exception.InvalidCredentialsException;
import com.flowpay.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private static final Logger logger =
            LoggerFactory.getLogger(AuthService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {

        logger.info("Login attempt for email={}", request.getEmail());

        Optional<User> optionalUser =
                userRepository.findByEmail(request.getEmail());

        if (optionalUser.isEmpty()) {

            logger.warn("Login failed. User not found: {}",
                    request.getEmail());

            throw new InvalidCredentialsException("User not found");
        }

        User user = optionalUser.get();

        boolean isPasswordMatched =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if (!isPasswordMatched) {

            logger.warn("Invalid password for user: {}",
                    request.getEmail());

            throw new InvalidCredentialsException("Invalid password");
        }

        logger.info("User '{}' logged in successfully.",
                user.getEmail());

        String token = jwtService.generateToken(user);

        return new LoginResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                token
        );
    }
}