package com.example.TaskManagementSystem.repository;

import com.example.TaskManagementSystem.models.Task;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @EntityGraph(value = "task-entity-graph")
    Optional<Task> findById(Long id);

    @EntityGraph(value = "task-entity-graph")
    List<Task> findAll();

    

}
