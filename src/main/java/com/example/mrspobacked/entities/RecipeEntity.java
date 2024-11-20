package com.example.mrspobacked.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

//todo: на удаление
/*
@Entity
@Table(name = "recipes")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeEntity extends BaseEntity {

    String recipeName;
    String description;
    Long totalCookingTime;
    String difficultyLevel;
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<RecipeIngredientEntity> recipeIngredients;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<RecipeStepEntity> steps;

    @ManyToOne // Каждый рецепт имеет одного пользователя (владельца)
    @JoinColumn(name = "user_id") // Это имя колонки в таблице рецептов, которое будет хранить идентификатор пользователя
    private UserEntity user;

}
*/
