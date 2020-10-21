package com.capstone.countertop.repositories;

import com.capstone.countertop.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Recipe findByTitle(String title);
    Recipe findFirstByTitle(String title);

    Recipe getOne(Long id);

}
