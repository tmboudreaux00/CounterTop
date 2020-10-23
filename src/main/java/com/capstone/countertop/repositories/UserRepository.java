package com.capstone.countertop.repositories;

import com.capstone.countertop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getOne(long id);
    User findByUsername(String username);
}
