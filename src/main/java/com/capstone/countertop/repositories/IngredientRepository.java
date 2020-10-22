package com.capstone.countertop.repositories;

import com.capstone.countertop.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>{

    Ingredient getOne(Long id);

    Ingredient findByName(String name);
}
