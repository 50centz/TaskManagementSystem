package com.example.TaskManagementSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    @NotBlank(message = "The title cannot be empty")
    private String comment;

    @NotNull(message = "The taskId cannot be empty")
    private Long taskId;
}
