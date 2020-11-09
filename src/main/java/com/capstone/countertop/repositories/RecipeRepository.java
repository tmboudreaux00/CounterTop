package com.capstone.countertop.repositories;

import com.capstone.countertop.models.Recipe;
import com.capstone.countertop.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Recipe getOne(Long id);

    List<Recipe> findAllByUserId(User id);
    Page<Recipe> findAllByUser(User id, Pageable pageable);

    @Query("FROM Recipe r WHERE r.name LIKE %:term% OR r.description LIKE %:term%")
    List<Recipe> searchRecipesByTerm(@Param("term") String term);

    @Query("FROM Recipe r WHERE r.name LIKE %:term%")
    List<Recipe> searchByNameLike(@Param("term") String term);

    @Query("FROM Recipe r WHERE r.description LIKE %:term%")
    List<Recipe> searchByDescriptionLike(@Param("term") String term);

}
