package com.example.mrspobacked.servises;

import com.example.mrspobacked.entities.IngredientEntity;
import com.example.mrspobacked.exceptions.IngredientNotFoundException;
import com.example.mrspobacked.repositories.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientEntity getById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException("Ингридиент с id: %d не найден".formatted(id)));
    }

    public List<IngredientEntity> getAll() {
        return ingredientRepository.findAll();
    }

    public void delete(Long id) {
        ingredientRepository.deleteById(id);
    }

    public IngredientEntity update(Long id, String name, String category) {
        IngredientEntity ingredient = getById(id);

        ingredient.setName(name);
        ingredient.setCategory(category);

        return save(ingredient);
    }
    public IngredientEntity save(IngredientEntity ingredientEntity) {
        return ingredientRepository.save(ingredientEntity);
    }
}
