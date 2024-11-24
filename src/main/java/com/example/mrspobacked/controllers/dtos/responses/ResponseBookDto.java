package com.example.mrspobacked.controllers.dtos.responses;

import com.example.mrspobacked.controllers.dtos.common.BookDto;
import com.example.mrspobacked.controllers.dtos.common.ComplexErrorDto;
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
public class ResponseBookDto {

    @Schema(description = "Книга")
    BookDto book;

    @Schema(description = "Описание ошибки")
    ComplexErrorDto error;
}
