package com.example.mrspobacked.controllers.dtos.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Общий ответ для обработки ошибок")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ComplexErrorDto {

    @Schema(description = "Временная метка ошибки")
    LocalDateTime timestamp;

    @Schema(description = "Код состояния HTTP")
    int status;

    @Schema(description = "Краткое описание ошибки")
    String error;

    String message;

    @Schema(description = "Детали ошибки")
    List<AdditionalInfo> details;
}
