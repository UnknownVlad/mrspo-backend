package com.example.mrspobacked.controllers.dtos.common;

import com.example.mrspobacked.converters.JsonListAttributeConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Lob;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.Map;

@Schema(description = "Book DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserActionDto {

    @Schema(description = "id пользователя", example = "114421")
    Long userId;

    @Schema(description = "url эндпоинта", example = "/api/book/get/all")
    String path;

    @Schema(description = "Тип запроса", example = "GET")
    String requestType;

    @Schema(description = "Имя java метода", example = "BookController#getAllBooks")
    String methodName;

    @Schema(description = "Параметры запроса", example = "[]")
    String requestParams;
}
