package com.example.TaskManagementSystem.dto.mapper;

import com.example.TaskManagementSystem.dto.TaskDto;
import com.example.TaskManagementSystem.models.Task;
import com.example.TaskManagementSystem.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskMapper {

    private final TaskRepository taskRepository;

    public TaskDto toDto(Task task) {
        TaskDto taskDto = new TaskDto();

        taskDto.setId(task.getId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setTaskStatus(task.getTaskStatus());
        taskDto.setPriority(task.getPriority());
        taskDto.setAuthorId(task.getAuthor().getId());
        taskDto.setExecutorId(task.getExecutor().getId());

        return taskDto;
    }


}
