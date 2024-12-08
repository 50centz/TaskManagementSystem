package com.example.TaskManagementSystem.services;

import com.example.TaskManagementSystem.dto.TaskDto;

public interface TaskService {

    TaskDto findById(Long id);

    TaskDto create(TaskDto taskDto);

    TaskDto update(Long taskId, TaskDto taskDto);

    void deleteById(long id);
}
