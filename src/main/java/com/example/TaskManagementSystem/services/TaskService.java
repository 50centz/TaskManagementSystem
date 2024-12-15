package com.example.TaskManagementSystem.services;

import com.example.TaskManagementSystem.dto.TaskDto;
import com.example.TaskManagementSystem.models.Task;

public interface TaskService {

    Task findById(Long taskId);

    TaskDto create(TaskDto taskDto);

    Task update(Long taskId, TaskDto taskDto);

    void deleteById(long taskId);

    Task updateByUser(Long taskId, TaskDto taskDto);
}
