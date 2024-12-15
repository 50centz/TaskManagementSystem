package com.example.TaskManagementSystem.dto;

import com.example.TaskManagementSystem.models.Comment;
import com.example.TaskManagementSystem.models.enums.Priority;
import com.example.TaskManagementSystem.models.enums.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private Long id;

    @NotBlank(message = "The title cannot be empty")
    private String title;

    @NotBlank(message = "The description cannot be empty")
    private String description;

    @NotNull(message = "The taskStatus cannot be empty")
    private TaskStatus taskStatus;

    @NotNull(message = "The priority cannot be empty")
    private Priority priority;

    @NotNull(message = "The authorId cannot be empty")
    private Long authorId;

    @NotNull(message = "The executorId cannot be empty")
    private Long executorId;

    private List<Comment> comments;
}
