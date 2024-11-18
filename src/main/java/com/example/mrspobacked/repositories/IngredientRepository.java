package com.example.mrspobacked.repositories;

import com.example.mrspobacked.entities.IngredientEntity;
import com.example.mrspobacked.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {

    List<IngredientEntity> findAllByName(String name);
    List<IngredientEntity> findAllByCategory(String category);

    @Query("""
        select i from IngredientEntity i where
            (:name is not null and i.name = :name) and
            (:category is not null and i.category = :category)
    """)
    List<IngredientEntity> findAllByNameAndCategory(
            @Param("name") String name,
            @Param("category") String category
    );

}
