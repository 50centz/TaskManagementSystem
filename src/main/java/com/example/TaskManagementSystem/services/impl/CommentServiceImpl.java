package com.example.TaskManagementSystem.services.impl;

import com.example.TaskManagementSystem.dto.CommentDto;
import com.example.TaskManagementSystem.exeptions.EntityNotFoundException;
import com.example.TaskManagementSystem.models.Comment;
import com.example.TaskManagementSystem.models.Task;
import com.example.TaskManagementSystem.repository.CommentRepository;
import com.example.TaskManagementSystem.repository.TaskRepository;
import com.example.TaskManagementSystem.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final TaskRepository taskRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Comment> findAllCommentByTaskId(Long id) {
        return commentRepository.findAllCommentByTaskId(id);
    }


    @PostAuthorize("returnObject.task.executor.username == authentication.principal.username or hasRole('ADMIN')")
    @Transactional
    @Override
    public Comment createComment(CommentDto commentDto) {

        Long taskId = commentDto.getTaskId();

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task with id %d not found".formatted(taskId)));

        Comment comment = new Comment();
        comment.setComment(comment.getComment());
        comment.setTask(task);

        return commentRepository.save(comment);
    }
}
