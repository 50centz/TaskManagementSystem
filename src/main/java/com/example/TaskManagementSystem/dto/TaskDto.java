package com.example.TaskManagementSystem.dto;

import com.example.TaskManagementSystem.enums.Priority;
import com.example.TaskManagementSystem.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private Long id;

    private String title;

    private String description;

    private TaskStatus taskStatus;

    private Priority priority;

    private Long authorId;

    private Long executorId;
}
