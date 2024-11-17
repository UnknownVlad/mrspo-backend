package com.example.mrspobacked.controllers.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Schema(description = "DTO ответа на запрос аутентификации")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthUserResponseDto {

    @Schema(description = "Успещныая ли аутентификация", example = "true")
    Boolean success;


}
