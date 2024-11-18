package com.example.mrspobacked.configuration;

import com.example.mrspobacked.entities.UserEntity;
import com.example.mrspobacked.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataConfiguration {

    @Bean
    public CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.existsByUsername("admin")) {
                UserEntity admin = UserEntity.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .authorities("ROLE_USER::ROLE_ADMIN")
                        .build();

                userRepository.save(admin);
            }

            if (!userRepository.existsByUsername("user")) {
                UserEntity user = UserEntity.builder()
                        .username("user")
                        .password(passwordEncoder.encode("user"))
                        .authorities("ROLE_USER")
                        .build();
                userRepository.save(user);
            }
        };
    }
}
