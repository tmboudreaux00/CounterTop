package com.capstone.countertop.repositories;

import com.capstone.countertop.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Recipe getOne(Long id);
    Recipe findByName(String name);
    Recipe findFirstByName(String name);
//    Recipe findOne(String skill);
    //Recipe findAllByDate(Date date);


    @Query("FROM Recipe r WHERE r.name LIKE %:term%")
    List<Recipe> searchByNameLike(@Param("term") String term);

    @Query("FROM Recipe r WHERE r.description LIKE %:term%")
    List<Recipe> searchByDescriptionLike(@Param("term") String term);


}
