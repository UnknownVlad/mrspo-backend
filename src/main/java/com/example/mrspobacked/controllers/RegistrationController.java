package com.example.mrspobacked.controllers;

import com.example.mrspobacked.controllers.dtos.RegistrationUserRequestDto;
import com.example.mrspobacked.controllers.dtos.RegistrationUserResponseDto;
import com.example.mrspobacked.servises.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@Tag(name = "Registration", description = "Операции для регистрации")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @PostMapping("/registration")
    @Operation(summary = "Регистрация пользователя", description = "Позволяет создать нового пользователя")
    public ResponseEntity<RegistrationUserResponseDto> registration(@RequestBody RegistrationUserRequestDto registrationUserRequestDto) {
        userService.createUser(registrationUserRequestDto);
        return ResponseEntity.ok(RegistrationUserResponseDto.builder().success(true).build());
    }
}
