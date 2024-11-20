package com.example.mrspobacked.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

//todo: на удаление
/*
@Entity
@Table(name = "recipes")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeStepEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    RecipeEntity recipe;

    Integer stepNumber;
    String description;



}
*/
