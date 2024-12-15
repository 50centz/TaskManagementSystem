package com.example.TaskManagementSystem.repository;

import com.example.TaskManagementSystem.models.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @EntityGraph(value = "comment-entity-graph")
    Optional<Comment> findById(Long id);

    @EntityGraph(value = "comment-entity-graph")
    List<Comment> findAll();

    @EntityGraph(value = "comment-entity-graph")
    List<Comment> findAllCommentByTaskId(Long id);

    void deleteAllByTaskId(long id);
}
