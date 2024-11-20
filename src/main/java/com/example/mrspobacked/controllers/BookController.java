package com.example.mrspobacked.controllers;

import com.example.mrspobacked.controllers.dtos.requests.RequestBookDto;
import com.example.mrspobacked.controllers.dtos.responses.DeleteBookResponseDto;
import com.example.mrspobacked.controllers.dtos.responses.ResponseBookDto;
import com.example.mrspobacked.entities.BookEntity;
import com.example.mrspobacked.mappers.BookMapper;
import com.example.mrspobacked.servises.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/book")
@Tag(name = "Books", description = "Добавление книг")
@RequiredArgsConstructor
@Slf4j
public class BookController {
    private final BookService bookService;

    private final BookMapper bookMapper;

    @PostMapping("/add")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "добавление книги", description = "Позволяет добавить новую книгу")
    public ResponseEntity<ResponseBookDto> addIngredient(@Valid @RequestBody RequestBookDto requestBookDto) {
        log.debug("IngredientController#addIngredient: {}", requestBookDto);
        BookEntity bookEntity = bookService.save(bookMapper.toEntity(requestBookDto));
        return ResponseEntity.ok(bookMapper.toDto(bookEntity));
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Получение книги", description = "Позволяет получить книгу по id")
    public ResponseEntity<ResponseBookDto> getIngredient(@PathVariable Long id) {
        log.debug("IngredientController#getIngredient: {}", id);
        BookEntity bookEntity = bookService.getById(id);

        return ResponseEntity.ok(bookMapper.toDto(bookEntity));
    }

    //todo: потенциально добавить пагинацию
    @GetMapping("/get/all")
    @Operation(summary = "Получить список всех книг", description = "Позволяет получить все книги")
    public ResponseEntity<List<ResponseBookDto>> getAllIngredients() {
        log.debug("IngredientController#getAllIngredients");
        List<BookEntity> bookEntities = bookService.getAll();

        return ResponseEntity.ok(bookEntities.stream().map(bookMapper::toDto).toList());
    }


    @PutMapping("/update/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Обновление книги", description = "Позволяет обновить существующую книгу")
    public ResponseEntity<ResponseBookDto> updateIngredient(@PathVariable Long id, @Valid @RequestBody RequestBookDto requestBookDto) {
        log.debug("IngredientController#updateIngredient: {}", id);
        BookEntity bookEntity = bookService.update(id, requestBookDto);
        return ResponseEntity.ok(bookMapper.toDto(bookEntity));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить книгу по id", description = "Позволяет удалить существующую книгу")
    public ResponseEntity<DeleteBookResponseDto> deleteRecipe(@PathVariable Long id) {
        bookService.delete(id);

        return ResponseEntity.ok(
                DeleteBookResponseDto.builder()
                        .success(true)
                        .build()
        );
    }

}
