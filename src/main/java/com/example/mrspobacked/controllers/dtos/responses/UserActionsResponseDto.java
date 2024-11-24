package com.example.mrspobacked.controllers.dtos.responses;

import com.example.mrspobacked.controllers.dtos.common.ComplexErrorDto;
import com.example.mrspobacked.controllers.dtos.common.UserActionDto;
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
public class UserActionsResponseDto {

    @Schema(description = "Список книг")
    Collection<UserActionDto> actions;

    @Schema(description = "Описание ошибки")
    ComplexErrorDto error;
}
