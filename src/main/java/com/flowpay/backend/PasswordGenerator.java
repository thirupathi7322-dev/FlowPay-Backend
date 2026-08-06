package com.flowpay.backend;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String hash =
                "$2a$10$E3SRpgFGcDSTqj0/YAWNmO5bOAofxA4/FsVdV1lLeSF941FqQFFca";

        System.out.println(
                encoder.matches("flowpay123", hash)
        );
    }
}