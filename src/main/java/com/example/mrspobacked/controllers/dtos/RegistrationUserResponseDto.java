package com.example.mrspobacked.controllers.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Schema(description = "User DTO для регистрации")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationUserResponseDto {

    @Schema(description = "Успещныая ли регистрация", example = "true")
    Boolean success;

}
