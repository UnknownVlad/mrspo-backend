package com.example.mrspobacked.controllers.dtos.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Детали ошибки валидации")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValidationInfo {

    @Schema(description = "Поле")
    String field;

    @Schema(description = "Проблема валидации")
    String description;
}