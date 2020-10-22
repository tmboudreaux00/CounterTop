package com.capstone.countertop.repositories;

//{} TAGS DENOTE MODEL AND OBJECT NAMES NOT BUILT OUT WHICH MAY BE RENAMED

import com.capstone.countertop.models.{RecipeRating};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecipeRatingRepository extends JpaRepository<{RecipeRating}, Long>{

        {RecipeRating}  findBy{Rating}({Boolean} rating);
}
