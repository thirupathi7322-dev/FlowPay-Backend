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

        logger.info("===== LOGIN API CALLED =====");
        logger.info("Login attempt for email={}", request.getEmail());

        Optional<User> optionalUser =
                userRepository.findByEmail(request.getEmail());

        logger.info("User found = {}", optionalUser.isPresent());

        if (optionalUser.isEmpty()) {

            logger.warn("Login failed. User not found: {}",
                    request.getEmail());

            throw new InvalidCredentialsException("User not found");
        }

        User user = optionalUser.get();

        logger.info("Database Email = {}", user.getEmail());
        logger.info("Entered Email  = {}", request.getEmail());

        logger.info("Raw Password = {}", request.getPassword());
        logger.info("DB Password Hash = [{}]", user.getPassword());
        logger.info("Hash Length = {}", user.getPassword().length());

        for (int i = 0; i < user.getPassword().length(); i++) {
            logger.info("Char {} = {}", i, (int) user.getPassword().charAt(i));
        }

        boolean isPasswordMatched =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        logger.info("Password matched = {}", isPasswordMatched);

        if (!isPasswordMatched) {

            logger.warn("Invalid password for user: {}",
                    request.getEmail());

            throw new InvalidCredentialsException("Invalid password");
        }

        logger.info("===== LOGIN SUCCESSFUL =====");
        logger.info("Generating JWT token...");

        String token = jwtService.generateToken(user);

        logger.info("JWT generated successfully.");

        return new LoginResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                token
        );
    }
}