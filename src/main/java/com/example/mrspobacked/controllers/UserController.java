package com.example.mrspobacked.controllers;

import com.example.mrspobacked.controllers.dtos.AuthUserRequestDto;
import com.example.mrspobacked.controllers.dtos.AuthUserResponseDto;
import com.example.mrspobacked.controllers.dtos.UserPageResponseDto;
import com.example.mrspobacked.entities.UserEntity;
import com.example.mrspobacked.servises.CustomAuthenticationManagerService;
import com.example.mrspobacked.servises.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/user")
@Tag(name = "Registration", description = "Операции для регистрации")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final CustomAuthenticationManagerService authenticationManagerService;

    @PostMapping("/auth")
    @Operation(summary = "Вход в аккаунт", description = "Позволяет войти в аккаунт")
    public ResponseEntity<AuthUserResponseDto> auth(@RequestBody AuthUserRequestDto authUserRequestDto) {
        boolean isAuthenticated = authenticationManagerService.authenticate(
                authUserRequestDto.getUsername(), authUserRequestDto.getPassword()
        );

        if (isAuthenticated) {
            return ResponseEntity.ok(new AuthUserResponseDto(true));
        } else {
            return ResponseEntity.status(401).body(new AuthUserResponseDto(false));
        }
    }


    @GetMapping("/page")
    @Operation(summary = "Страница пользователя", description = "Позволяет посмотреть информацию о пользователе")
    public ResponseEntity<UserPageResponseDto> registration() {
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
