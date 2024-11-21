package com.example.mrspobacked.configuration;

import com.example.mrspobacked.entities.BookEntity;
import com.example.mrspobacked.entities.UserEntity;
import com.example.mrspobacked.repositories.BookRepository;
import com.example.mrspobacked.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataConfiguration {

    @Bean
    public CommandLineRunner initClientDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
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

    @Bean
    public CommandLineRunner initBookDatabase(BookRepository bookRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            BookEntity book1 = BookEntity.builder()
                    .bookName("Гари Философский")
                    .description("Очень крутая книга")
                    .pageCount(300)
                    .circulation(200)
                    .authors("Крутой А.А::Забавный В.В")
                    .genres("Триллер::Комедия")
                    .rating(4.2)
                    .onSale(true)
                    .build();
            BookEntity book2 = BookEntity.builder()
                    .bookName("Саня с маслом")
                    .description("Норм чтиво")
                    .pageCount(123)
                    .circulation(111)
                    .authors("Веселый В.А:: Злой П.Р")
                    .genres("Драмма::Комедия")
                    .rating(4.1)
                    .onSale(false)
                    .build();
            bookRepository.save(book1);
            bookRepository.save(book2);
        };
    }
}
