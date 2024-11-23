package com.example.mrspobacked.exceptions.hendler;

import com.example.mrspobacked.controllers.BookController;
import com.example.mrspobacked.controllers.UserController;
import com.example.mrspobacked.controllers.dtos.common.AdditionalInfo;
import com.example.mrspobacked.controllers.dtos.common.CommonErrorResponseDto;
import com.example.mrspobacked.controllers.dtos.common.ComplexErrorDto;
import com.example.mrspobacked.controllers.dtos.common.ValidationInfo;
import com.example.mrspobacked.exceptions.BookNotFoundException;
import com.example.mrspobacked.exceptions.UserAlreadyExistsException;
import com.example.mrspobacked.exceptions.UserNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice(assignableTypes = {
        UserController.class,
        BookController.class
})
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.debug("Отловлена ошибка MethodArgumentNotValidException: {}", ex.getMessage());
        List<ValidationInfo> validationDetails = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::mapFieldErrorToValidationInfo)
                .toList();

        ComplexErrorDto errorDto = ComplexErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Ошибка валидации параметров запроса")
                .validationDetails(validationDetails)
                .build();

        CommonErrorResponseDto response = CommonErrorResponseDto.builder()
                .error(errorDto)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CommonErrorResponseDto> handleConstraintViolationException(ConstraintViolationException ex) {
        List<ValidationInfo> validationDetails = ex.getConstraintViolations()
                .stream()
                .map(this::mapConstraintViolationToValidationInfo)
                .collect(Collectors.toList());

        ComplexErrorDto errorDto = ComplexErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Ошибка валидации параметров запроса")
                .validationDetails(validationDetails)
                .build();

        CommonErrorResponseDto response = CommonErrorResponseDto.builder()
                .error(errorDto)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<CommonErrorResponseDto> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(buildDefaultError(ex, HttpStatus.CONFLICT));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CommonErrorResponseDto> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildDefaultError(ex, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<CommonErrorResponseDto> handleBookNotFoundException(BookNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildDefaultError(ex, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CommonErrorResponseDto> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(buildDefaultError(ex, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    private CommonErrorResponseDto buildDefaultError(Exception ex, HttpStatus status) {
        log.error("Отловлена ошибка {}: {}", ex.getClass(), ex.getMessage(), ex);
        return CommonErrorResponseDto.builder()
                .error(
                        ComplexErrorDto.builder()
                                .timestamp(LocalDateTime.now())
                                .status(status.value())
                                .error(status.getReasonPhrase())
                                .message(ex.getMessage())
                                .build()
                )
                .build();

    }

    private ValidationInfo mapFieldErrorToValidationInfo(FieldError fieldError) {
        return ValidationInfo.builder()
                .field(fieldError.getField())
                .description(fieldError.getDefaultMessage())
                .build();
    }

    private ValidationInfo mapConstraintViolationToValidationInfo(ConstraintViolation<?> violation) {
        return ValidationInfo.builder()
                .field(violation.getPropertyPath().toString())
                .description(violation.getMessage())
                .build();
    }


}
