package com.example.mrspobacked.controllers;

import com.example.mrspobacked.controllers.dtos.requests.AuthUserRequestDto;
import com.example.mrspobacked.controllers.dtos.requests.RegistrationUserRequestDto;
import com.example.mrspobacked.controllers.dtos.responses.AuthUserResponseDto;
import com.example.mrspobacked.controllers.dtos.responses.DeleteIngredientResponseDto;
import com.example.mrspobacked.controllers.dtos.responses.RegistrationUserResponseDto;
import com.example.mrspobacked.controllers.dtos.responses.UserPageResponseDto;
import com.example.mrspobacked.entities.UserEntity;
import com.example.mrspobacked.servises.CustomAuthenticationManagerService;
import com.example.mrspobacked.servises.JwtService;
import com.example.mrspobacked.servises.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/user")
@Tag(name = "Registration", description = "Операции для пользователя")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    private final JwtService jwtService;

    @PostMapping("/registration")
    @Operation(summary = "Регистрация пользователя", description = "Позволяет создать нового пользователя")
    public ResponseEntity<RegistrationUserResponseDto> registration(@Valid @RequestBody RegistrationUserRequestDto registrationUserRequestDto) {
        log.debug("RegistrationController#registration: {}", registrationUserRequestDto);
        userService.createUser(registrationUserRequestDto);
        return ResponseEntity.ok(RegistrationUserResponseDto.builder().success(true).build());
    }

    @PostMapping("/auth")
    @Operation(summary = "Вход в аккаунт", description = "Позволяет войти в аккаунт")
    public ResponseEntity<AuthUserResponseDto> auth(@Valid  @RequestBody AuthUserRequestDto authUserRequestDto) {
        log.debug("UserController#auth: {}", authUserRequestDto);

        UserDetails userEntity = userService.loadUserByUsername(authUserRequestDto.getUsername());
        String jwtToken = jwtService.generateToken(userEntity);
        return ResponseEntity.ok(AuthUserResponseDto.builder().success(true).token(jwtToken).expirationTime(jwtService.getExpirationTime()).build());
    }

    @GetMapping("/page")
    @Operation(summary = "Страница пользователя", description = "Позволяет посмотреть информацию о пользователе")
    public ResponseEntity<UserPageResponseDto> page() {
        log.debug("UserController#page");
        UserEntity currentUser = userService.currentUser();
        return ResponseEntity.ok(
                UserPageResponseDto.builder()
                        .id(currentUser.getId())
                        .username(currentUser.getUsername())
                        .roles(currentUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                        .build()
        );
    }

}