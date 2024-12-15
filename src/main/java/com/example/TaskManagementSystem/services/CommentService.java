package com.example.TaskManagementSystem.services;

import com.example.TaskManagementSystem.dto.CommentDto;
import com.example.TaskManagementSystem.models.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findAllCommentByTaskId(Long id);

    Comment createComment(CommentDto commentDto);
}
