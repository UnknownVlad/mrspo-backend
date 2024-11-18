package com.example.mrspobacked.controllers;

import com.example.mrspobacked.controllers.dtos.requests.IngredientRequestDto;
import com.example.mrspobacked.controllers.dtos.responses.AddIngredientResponseDto;
import com.example.mrspobacked.controllers.dtos.responses.DeleteIngredientResponseDto;
import com.example.mrspobacked.controllers.dtos.responses.GetIngredientResponseDto;
import com.example.mrspobacked.entities.IngredientEntity;
import com.example.mrspobacked.servises.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/ingredient")
@Tag(name = "Ingredient", description = "Доьавление ингридиентов")
@RequiredArgsConstructor
@Slf4j
public class IngredientController {
    private final IngredientService ingredientService;

    @PostMapping("/add")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "добавление ингридиента", description = "Позволяет добавить новый ингридиент")
    public ResponseEntity<AddIngredientResponseDto> addIngredient(@Valid @RequestBody IngredientRequestDto ingredientRequestDto) {
        log.debug("IngredientController#addIngredient: {}", ingredientRequestDto);
        IngredientEntity ingredientEntity = ingredientService.save(
                IngredientEntity.builder()
                        .name(ingredientRequestDto.getName())
                        .category(ingredientRequestDto.getCategory())
                        .build()
        );
        return ResponseEntity.ok(
                AddIngredientResponseDto.builder()
                        .id(ingredientEntity.getId())
                        .category(ingredientEntity.getCategory())
                        .name(ingredientEntity.getName())
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Получение ингридиента", description = "Позволяет плдучить ингридиент по id")
    public ResponseEntity<GetIngredientResponseDto> getIngredient(@PathVariable Long id) {
        log.debug("IngredientController#getIngredient: {}", id);
        IngredientEntity ingredientEntity = ingredientService.getById(id);

        return ResponseEntity.ok(
                GetIngredientResponseDto.builder()
                        .id(ingredientEntity.getId())
                        .category(ingredientEntity.getCategory())
                        .name(ingredientEntity.getName())
                        .build()
        );
    }

    //todo: потенциально добавить пагинацию
    @GetMapping("/get/all")
    @Operation(summary = "Получить список всех ингридиентов", description = "Позволяет получить все ингридиенты")
    public ResponseEntity<List<GetIngredientResponseDto>> getAllIngredients() {
        log.debug("IngredientController#getAllIngredients");
        List<IngredientEntity> ingredientEntities = ingredientService.getAll();

        return ResponseEntity.ok(
                ingredientEntities.stream().map(
                        ingredientEntity ->
                                GetIngredientResponseDto.builder()
                                        .id(ingredientEntity.getId())
                                        .category(ingredientEntity.getCategory())
                                        .name(ingredientEntity.getName())
                                        .build()
                        ).toList()
        );
    }


    @PutMapping("/update/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Обновление ингридиента", description = "Позволяет обновить существующий ингридиент")
    public ResponseEntity<GetIngredientResponseDto> updateIngredient(@PathVariable Long id, @Valid @RequestBody IngredientRequestDto ingredientRequestDto) {
        log.debug("IngredientController#updateIngredient: {}", id);
        IngredientEntity ingredientEntity = ingredientService.update(
                id, ingredientRequestDto.getName(), ingredientRequestDto.getCategory()
        );
        return ResponseEntity.ok(
                GetIngredientResponseDto.builder()
                        .id(ingredientEntity.getId())
                        .category(ingredientEntity.getCategory())
                        .name(ingredientEntity.getName())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить ингридиент", description = "Позволяет удалить существующий ингридиент")
    public ResponseEntity<DeleteIngredientResponseDto> deleteRecipe(@PathVariable Long id) {
        ingredientService.delete(id);

        return ResponseEntity.ok(
                DeleteIngredientResponseDto.builder()
                        .success(true)
                        .build()
        );
    }

}
