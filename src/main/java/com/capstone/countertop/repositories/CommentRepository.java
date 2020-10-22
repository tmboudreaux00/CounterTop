package com.capstone.countertop.repositories;

import com.capstone.countertop.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>{

    Comment getOne(long id);

    @Query("FROM comments c WHERE c.comment_body LIKE %:term%") //c.text references text field for comments - may need to be changed
    List<Comment> searchByDescriptionLike(@Param("term") String term);
}
