package com.example.TaskManagementSystem.controllers;

import com.example.TaskManagementSystem.dto.CommentDto;
import com.example.TaskManagementSystem.services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Создание комментариев ", description = "Позволяет создать комментарий")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(
            summary = "Создание комментария",
            description = "Позволяет создать комментарий, только если вы АДМИН или исполнитель задачи"
    )
    @PostMapping("/api/comment/create")
    public ResponseEntity<?> createComment(@Valid @RequestBody CommentDto commentDto) {
        commentService.createComment(commentDto);
        return new ResponseEntity<Object>("Comment created", HttpStatus.CREATED);
    }

}
