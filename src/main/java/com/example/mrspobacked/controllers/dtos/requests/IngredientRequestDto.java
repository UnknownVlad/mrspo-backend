package com.example.mrspobacked.controllers.dtos.requests;

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
public class IngredientRequestDto {

    @Schema(description = "Название ингридиента", example = "Молоко простоквашино")
    @NotBlank(message = "Поле является обязательным для заполнения")
    @Size(min = 1, message = "Длина поля должна быть больше 1 символа")
    String name;

    @Schema(description = "Категория ингридиента", example = "Какая-то категория")
    @NotBlank(message = "Поле является обязательным для заполнения")
    @Size(min = 1, message = "Длина поля должна быть больше 1 символа")
    String category;
}
