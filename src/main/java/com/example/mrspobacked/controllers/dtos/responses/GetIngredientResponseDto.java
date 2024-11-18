package com.example.mrspobacked.controllers.dtos.responses;

import com.example.mrspobacked.controllers.dtos.common.ComplexErrorDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Schema(description = "Dto ответа на добавление ингридиента")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetIngredientResponseDto {
    @Schema(description = "id ингридиента", example = "114421")
    Long id;

    @Schema(description = "Название ингридиента", example = "Молоко простоквашино")
    String name;

    @Schema(description = "Категория ингридиента", example = "Какая-то категория")
    String category;

    @Schema(description = "Описание ошибки")
    ComplexErrorDto error;
}
