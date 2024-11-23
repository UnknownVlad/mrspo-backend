package com.example.mrspobacked.controllers.dtos.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Детали ошибки")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdditionalInfo {

    @Schema(description = "Описание дополнительной проблемы")
    String description;
}