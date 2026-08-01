package com.flowpay.backend.service;

import com.flowpay.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.flowpay.backend.dto.LoginRequest;
import com.flowpay.backend.dto.LoginResponse;
import com.flowpay.backend.entity.User;
import com.flowpay.backend.exception.InvalidCredentialsException;

import java.util.Optional;

@Service
public class AuthService {

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

        System.out.println("===== Login API called =====");

        Optional<User> optionalUser =
                userRepository.findByEmail(request.getEmail());

        System.out.println("User found: " + optionalUser.isPresent());

        if (optionalUser.isEmpty()) {
            throw new InvalidCredentialsException("User not found");
        }

        User user = optionalUser.get();

        System.out.println("Database Email: " + user.getEmail());
        System.out.println("Entered Email : " + request.getEmail());

        System.out.println("Database Password: " + user.getPassword());
        System.out.println("Entered Password : " + request.getPassword());

        boolean isPasswordMatched =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        System.out.println("Password Matched: " + isPasswordMatched);

        if (!isPasswordMatched) {
            throw new InvalidCredentialsException("Invalid password");
        }

        System.out.println("===== Login Successful =====");
        String token = jwtService.generateToken(user);

        return new LoginResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                token
        );
    }
}