package com.example.mrspobacked.controllers.dtos.responses;

import com.example.mrspobacked.controllers.dtos.common.ComplexErrorDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Schema(description = "Dto ответа на удаление ингридиента")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeleteBookResponseDto {

    @Schema(description = "Успешное ли удаление", example = "true")
    Boolean success;

    @Schema(description = "Описание ошибки")
    ComplexErrorDto error;
}
