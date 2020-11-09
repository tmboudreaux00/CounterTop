package com.capstone.countertop.repositories;

import com.capstone.countertop.models.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long>{

    Favorite getOne(Long id);

    Favorite findFirstByRecipeIdEqualsAndApiRecipeEquals(long id, boolean api);
}
