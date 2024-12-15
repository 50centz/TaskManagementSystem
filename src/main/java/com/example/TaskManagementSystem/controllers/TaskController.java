package com.example.TaskManagementSystem.controllers;


import com.example.TaskManagementSystem.dto.TaskDto;
import com.example.TaskManagementSystem.dto.mapper.TaskMapper;
import com.example.TaskManagementSystem.models.Comment;
import com.example.TaskManagementSystem.services.CommentService;
import com.example.TaskManagementSystem.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Создание, обновление, удаление и просмотр задач")
@Validated
@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    private final TaskMapper taskMapper;

    private final CommentService commentService;


    @Operation(
            summary = "Получить задачу",
            description = "Позволяет получить задачу, только если вы АДМИН или исполнитель задачи"
    )
    @GetMapping("/api/task/viewing/task/{id}")
    public ResponseEntity<TaskDto> viewing(@Valid @PathVariable("id") @Min(0) Long id) {

        TaskDto taskDto = taskMapper.toDto(taskService.findById(id));

        List<Comment> comments = commentService.findAllCommentByTaskId(id);

        taskDto.setComments(comments);

        return  new ResponseEntity<>(taskDto, HttpStatus.OK);
    }


    @Operation(
            summary = "Создать задачу",
            description = "Позволяет создать задачу, только если вы АДМИН"
    )
    @PostMapping("/api/task/create")
    public ResponseEntity<TaskDto> create(@RequestBody TaskDto taskDto) {

        TaskDto newTaskDto = taskService.create(taskDto);

        return  new ResponseEntity<>(newTaskDto, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Обновить задачу",
            description = "Позволяет обновить задачу, только если вы АДМИН"
    )
    @PostMapping("/api/task/edit/{id}")
    public ResponseEntity<TaskDto> update(@PathVariable("id") @Min(0) Long id, @Valid @RequestBody TaskDto taskDto) {

        TaskDto updateTaskDto = taskMapper.toDto(taskService.update(id, taskDto));

        return  new ResponseEntity<>(updateTaskDto, HttpStatus.OK);
    }


    @Operation(
            summary = "Удалить задачу",
            description = "Позволяет удалить задачу и все комментарии к ней только если вы АДМИН"
    )
    @DeleteMapping("/api/task/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteById(@PathVariable("id") @Min(0) Long id) {

        taskService.deleteById(id);

    }

    @Operation(
            summary = "Обновить задачу",
            description = "Позволяет обновить задачу, только если вы исполнитель задачи"
    )
    @PostMapping("/api/task/edit/by-user/{id}")
    public ResponseEntity<TaskDto> updateByUser(@PathVariable("id") @Min(0) Long id, @Valid @RequestBody TaskDto taskDto) {

        TaskDto updateTaskDto = taskMapper.toDto(taskService.updateByUser(id, taskDto));

        return  new ResponseEntity<>(updateTaskDto, HttpStatus.OK);
    }

}
