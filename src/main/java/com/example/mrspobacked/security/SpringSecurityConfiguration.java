package com.example.mrspobacked.security;

import com.example.mrspobacked.servises.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfiguration {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/registration").permitAll()
                        .requestMatchers("/api/user/auth").permitAll()
                        .requestMatchers("/api/user/page").authenticated()
                        .requestMatchers("/v3/api-docs", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                        .anyRequest().permitAll()
                )

                /*.formLogin((form) -> form
                        .loginPage("/api/user/auth")
                        .permitAll()
                )*/
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }
}
