package com.example.mrspobacked.controllers.dtos.responses;

import com.example.mrspobacked.controllers.dtos.common.ComplexErrorDto;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "jwt токен пользователя")
    String token;
    @Schema(description = "время жизни токена")
    Long expirationTime;

    @Schema(description = "Описание ошибки")
    ComplexErrorDto error;

}
