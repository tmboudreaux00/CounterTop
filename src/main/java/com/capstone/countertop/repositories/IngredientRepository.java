package com.capstone.countertop.repositories;

import com.capstone.countertop.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>{

    Ingredient getOne(Long id);

    Ingredient findByName(String name);
}
