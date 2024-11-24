package com.example.mrspobacked.controllers;

import com.example.mrspobacked.controllers.dtos.requests.RequestBookDto;
import com.example.mrspobacked.controllers.dtos.responses.AllBookResponseDto;
import com.example.mrspobacked.controllers.dtos.responses.DeleteBookResponseDto;
import com.example.mrspobacked.controllers.dtos.responses.ResponseBookDto;
import com.example.mrspobacked.entities.BookEntity;
import com.example.mrspobacked.mappers.BookMapper;
import com.example.mrspobacked.servises.BookService;
import com.example.mrspobacked.servises.UserActionService;
import com.example.mrspobacked.servises.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Validated
@RestController
@RequestMapping("/api/book")
@Tag(name = "Books", description = "Добавление книг")
@RequiredArgsConstructor
@Slf4j
public class BookController {
    private final UserActionService userActionService;
    private final UserService userService;
    private final BookService bookService;
    private final BookMapper bookMapper;
    private final ObjectMapper objectMapper;


    @PostMapping("/add")
    @Operation(summary = "Добавление книги", description = "Позволяет добавить новую книгу (authenticated)")
    public ResponseEntity<ResponseBookDto> addBook(@Valid @RequestBody RequestBookDto requestBookDto) {
        log.debug("BookController#addBook: {}", requestBookDto);
        userActionService.createAndSaveUserAction(
                userService.currentUserId(),
                "/api/book/add",
                "POST",
                "BookController#addBook",
                Map.of("requestBookDto", objectMapper.convertValue(requestBookDto, Map.class))
        );

        BookEntity bookEntity = bookService.save(bookMapper.toEntity(requestBookDto));
        return ResponseEntity.ok(ResponseBookDto.builder().book(bookMapper.toDto(bookEntity)).build());
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Получение книги", description = "Позволяет получить книгу по id (authenticated)")
    public ResponseEntity<ResponseBookDto> getBookById(@PathVariable Long id) {
        log.debug("BookController#getBookById: {}", id);
        userActionService.createAndSaveUserAction(
                userService.currentUserId(),
                "/api/book/get/{id}",
                "GET",
                "BookController#getBookById",
                Map.of("id", id)
        );

        BookEntity bookEntity = bookService.getById(id);

        return ResponseEntity.ok(ResponseBookDto.builder().book(bookMapper.toDto(bookEntity)).build());
    }

    //todo: отредактировать эндпоинт
    /*@GetMapping("/get/{nameBook}")
    @Operation(summary = "Получение книги", description = "Позволяет получить книгу по подобию названия (authenticated)")
    public ResponseEntity<List<ResponseBookDto>> getBookByName(@PathVariable String nameBook) {
        log.debug("BookController#getBookByName: {}", nameBook);
        userActionService.createAndSaveUserAction(
                userService.currentUserId(),
                "/api/book/get/{nameBook}",
                "GET",
                "BookController#getBookByName",
                Map.of("nameBook", nameBook)
        );

        List<BookEntity> bookEntities = bookService.getByBookNameLike(nameBook);

        return ResponseEntity.ok(bookEntities.stream().map(bookMapper::toDto).toList());
    }*/

    //todo: потенциально добавить пагинацию
    @GetMapping("/get/all")
    @Operation(summary = "Получить список всех книг", description = "Позволяет получить все книги (authenticated)")
    public ResponseEntity<AllBookResponseDto> getAllBooks() {
        log.debug("BookController#getAllBooks");
        userActionService.createAndSaveUserAction(
                userService.currentUserId(),
                "/api/book/get/all",
                "GET",
                "BookController#getAllBooks",
                Map.of()
        );
        List<BookEntity> bookEntities = bookService.getAll();

        return ResponseEntity.ok(
                AllBookResponseDto.builder()
                        .books(bookEntities.stream().map(bookMapper::toDto).toList())
                        .build()
                );
    }


    @PutMapping("/update/{id}")
    @Operation(summary = "Обновление книги", description = "Позволяет обновить существующую книгу (authenticated)")
    public ResponseEntity<ResponseBookDto> updateBook(@PathVariable Long id, @Valid @RequestBody RequestBookDto requestBookDto) {
        log.debug("BookController#updateBook: {}", id);

        userActionService.createAndSaveUserAction(
                userService.currentUserId(),
                "/api/book/update/{id}",
                "PUT",
                "BookController#updateBook",
                Map.of(
                        "id", id,
                        "requestBookDto", objectMapper.convertValue(requestBookDto, Map.class)
                )
        );

        BookEntity bookEntity = bookService.update(id, requestBookDto);
        return ResponseEntity.ok(ResponseBookDto.builder().book(bookMapper.toDto(bookEntity)).build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить книгу по id", description = "Позволяет удалить существующую книгу по id (authenticated)")
    public ResponseEntity<DeleteBookResponseDto> deleteBook(@PathVariable Long id) {
        log.debug("BookController#deleteBook: {}", id);
        userActionService.createAndSaveUserAction(
                userService.currentUserId(),
                "/api/book/delete/{id}",
                "DELETE",
                "BookController#deleteBook",
                Map.of("id", id)
        );

        bookService.delete(id);

        return ResponseEntity.ok(
                DeleteBookResponseDto.builder()
                        .success(true)
                        .build()
        );
    }

}
