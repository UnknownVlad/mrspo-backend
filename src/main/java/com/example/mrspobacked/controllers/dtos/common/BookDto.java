package com.example.mrspobacked.controllers.dtos.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;

@Schema(description = "Book DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDto {
    @Schema(description = "id книги", example = "114421")
    Long id;

    @Schema(description = "Название книги", example = "Гарри Потер")
    String bookName;

    @Schema(description = "Описание книги", example = "Интересная книга")
    String description;

    @Schema(description = "Кол-во старниц", example = "430")
    Integer pageCount;

    @Schema(description = "Тираж книги", example = "400")
    Integer circulation;

    @Schema(description = "Авторы", example = "[\"Стетхем А.А\", \"Дизель В.С\"]")
    Collection<String> authors;

    @Schema(description = "Жанры книги", example = "[\"Триллер\", \"Комедия\"]")
    Collection<String> genres;

    @Schema(description = "Рейтинг книги", example = "4.2")
    Double rating;

    @Schema(description = "В продаже ли книга", example = "true")
    Boolean onSale;
}
