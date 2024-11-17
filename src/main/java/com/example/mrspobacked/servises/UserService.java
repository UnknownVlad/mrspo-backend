package com.example.mrspobacked.servises;

import com.example.mrspobacked.controllers.dtos.RegistrationUserRequestDto;
import com.example.mrspobacked.entities.UserEntity;
import com.example.mrspobacked.exceptions.UserAlreadyExistsException;
import com.example.mrspobacked.exceptions.UserNotFoundException;
import com.example.mrspobacked.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("Пользователь с таким именем не найден"));
    }

    @Transactional
    public UserDetails createUser(RegistrationUserRequestDto registrationUserRequestDto) {
        if (userRepository.existsByUsername(registrationUserRequestDto.getUsername()))
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует");

        UserEntity userEntity = UserEntity.builder()
                .username(registrationUserRequestDto.getUsername())
                .password(passwordEncoder.encode(registrationUserRequestDto.getPassword()))
                .authorities("ROLE_USER")
                .build();

        return userRepository.save(userEntity);
    }

    public UserEntity currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserNotFoundException("Пользователь не аутентифицирован");
        }

        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с таким именем не найден"));
    }

}
