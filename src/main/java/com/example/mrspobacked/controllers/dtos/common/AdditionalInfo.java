package com.example.mrspobacked.controllers.dtos.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Детали ошибки валидации")
public class AdditionalInfo {
    @Schema(description = "Сообщение ошибки")
    private String message;
}