package com.example.TaskManagementSystem.services.impl;

import com.example.TaskManagementSystem.dto.TaskDto;
import com.example.TaskManagementSystem.dto.mapper.TaskMapper;
import com.example.TaskManagementSystem.exeptions.EntityNotFoundException;
import com.example.TaskManagementSystem.models.Task;
import com.example.TaskManagementSystem.models.User;
import com.example.TaskManagementSystem.repository.TaskRepository;
import com.example.TaskManagementSystem.repository.UserRepository;
import com.example.TaskManagementSystem.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    private final TaskMapper taskMapper;


    @Transactional(readOnly = true)
    @Override
    public TaskDto findById(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task with id %d not found".formatted(taskId)));

        return taskMapper.toDto(task);

    }

    @Transactional
    @Override
    public TaskDto create(TaskDto taskDto) {

        Long authorId = taskDto.getAuthorId();

        Long executorId = taskDto.getExecutorId();

        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author with id %d not found".formatted(authorId)));
        User executor = userRepository.findById(executorId)
                .orElseThrow(() -> new EntityNotFoundException("Executor with id %d not found".formatted(executorId)));

        Task task = taskRepository.save(new Task(0L, taskDto.getTitle(), taskDto.getDescription(),
                taskDto.getTaskStatus(), taskDto.getPriority(), author, executor));

        return taskMapper.toDto(task);
    }

    @Transactional
    @Override
    public TaskDto update(Long taskId, TaskDto taskDto) {

        Long authorId = taskDto.getAuthorId();

        Long executorId = taskDto.getExecutorId();

        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author with id %d not found".formatted(authorId)));
        User executor = userRepository.findById(executorId)
                .orElseThrow(() -> new EntityNotFoundException("Executor with id %d not found".formatted(executorId)));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task with id %d not found".formatted(taskId)));

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setTaskStatus(taskDto.getTaskStatus());
        task.setPriority(taskDto.getPriority());
        task.setAuthor(author);
        task.setExecutor(executor);

        Task newTask = taskRepository.save(task);

        return taskMapper.toDto(newTask);
    }

    @Transactional
    @Override
    public void deleteById(long taskId) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task with id %d not found".formatted(taskId)));

        taskRepository.deleteById(taskId);

    }

}
