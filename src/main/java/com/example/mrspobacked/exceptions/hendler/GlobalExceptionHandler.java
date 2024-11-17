package com.example.mrspobacked.exceptions.hendler;

import com.example.mrspobacked.controllers.UserController;
import com.example.mrspobacked.controllers.dtos.common.AdditionalInfo;
import com.example.mrspobacked.controllers.dtos.common.CommonErrorResponseDto;
import com.example.mrspobacked.controllers.dtos.common.ComplexErrorDto;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice(assignableTypes = {
        UserController.class
})
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.debug("Отловлена ошибка MethodArgumentNotValidException: {}", ex.getMessage());
        List<AdditionalInfo> details = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new AdditionalInfo("Поле: %s не удовлетворяет: %s".formatted(error.getField(), error.getDefaultMessage())))
                .toList();

        ComplexErrorDto response = ComplexErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Error")
                .details(details)
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonErrorResponseDto(response));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CommonErrorResponseDto> handleConstraintViolationException(ConstraintViolationException ex) {
        log.debug("Отловлена ошибка ConstraintViolationException: {}", ex.getMessage());
        List<AdditionalInfo> details = ex.getConstraintViolations().stream()
                .map(violation -> new AdditionalInfo(
                        "%s, %s".formatted(violation.getPropertyPath().toString(), violation.getMessage())
                ))
                .toList();

        ComplexErrorDto response = ComplexErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Error")
                .details(details)
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonErrorResponseDto(response));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CommonErrorResponseDto> handleRuntimeException(RuntimeException ex) {
        log.debug("Отловлена ошибка RuntimeException: {}", ex.getMessage());
        ComplexErrorDto response = ComplexErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonErrorResponseDto(response));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonErrorResponseDto> handleException(Exception ex) {
        log.debug("Отловлена ошибка Exception: {}", ex.getMessage());
        ComplexErrorDto response = ComplexErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Unexpected Error")
                .message(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonErrorResponseDto(response));
    }

}
