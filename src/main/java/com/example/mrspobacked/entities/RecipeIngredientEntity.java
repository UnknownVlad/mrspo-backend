package com.example.mrspobacked.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

//todo: на удаление
/*@Entity
@Table(name = "recipe_ingredient")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeIngredientEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    RecipeEntity recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    IngredientEntity ingredient;

    Double amount;
    String unit;
}*/
