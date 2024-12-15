package com.example.TaskManagementSystem.services.impl;

import com.example.TaskManagementSystem.dto.TaskDto;
import com.example.TaskManagementSystem.dto.mapper.TaskMapper;
import com.example.TaskManagementSystem.exeptions.EntityNotFoundException;
import com.example.TaskManagementSystem.models.Comment;
import com.example.TaskManagementSystem.models.Task;
import com.example.TaskManagementSystem.models.User;
import com.example.TaskManagementSystem.repository.CommentRepository;
import com.example.TaskManagementSystem.repository.TaskRepository;
import com.example.TaskManagementSystem.repository.security.UserRepository;
import com.example.TaskManagementSystem.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    private final TaskMapper taskMapper;

    private final CommentRepository commentRepository;


    @PostAuthorize("returnObject.executor.username == authentication.principal.username or hasRole('ADMIN')")
    @Transactional(readOnly = true)
    @Override
    public Task findById(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task with id %d not found".formatted(taskId)));
        return task;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public TaskDto create(TaskDto taskDto) {

        Long authorId = taskDto.getAuthorId();

        Long executorId = taskDto.getExecutorId();

        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author with id %d not found".formatted(authorId)));
        User executor = userRepository.findById(executorId)
                .orElseThrow(() -> new EntityNotFoundException("Executor with id %d not found".formatted(executorId)));

        Task task = taskRepository.save(new Task(null, taskDto.getTitle(), taskDto.getDescription(),
                taskDto.getTaskStatus(), taskDto.getPriority(), author, executor));

        return taskMapper.toDto(task);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public Task update(Long taskId, TaskDto taskDto) {

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

        return taskRepository.save(task);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public void deleteById(long taskId) {

        List<Comment> comments = commentRepository.findAllCommentByTaskId(taskId);

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task with id %d not found".formatted(taskId)));

        if (comments != null) {
            commentRepository.deleteAllByTaskId(taskId);
        }
        taskRepository.deleteById(taskId);


    }

    @PostAuthorize("returnObject.executor.username == authentication.principal.username")
    @Transactional
    @Override
    public Task updateByUser(Long taskId, TaskDto taskDto) {
        Long authorId = taskDto.getAuthorId();

        Long executorId = taskDto.getExecutorId();

        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Author with id %d not found".formatted(authorId)));
        User executor = userRepository.findById(executorId)
                .orElseThrow(() -> new EntityNotFoundException("Executor with id %d not found".formatted(executorId)));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task with id %d not found".formatted(taskId)));

        task.setTaskStatus(taskDto.getTaskStatus());

        return task;

    }

}
