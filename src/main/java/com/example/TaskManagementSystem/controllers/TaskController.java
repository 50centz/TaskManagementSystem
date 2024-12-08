package com.example.TaskManagementSystem.controllers;


import com.example.TaskManagementSystem.dto.TaskDto;
import com.example.TaskManagementSystem.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;


    @GetMapping("/api/task/viewing/task/{id}")
    public ResponseEntity<TaskDto> viewing(@PathVariable("id") Long id) {

        TaskDto taskDto = taskService.findById(id);

        return  new ResponseEntity<>(taskDto, HttpStatus.OK);
    }


    @PostMapping("/api/task/create")
    public ResponseEntity<TaskDto> create(@RequestBody TaskDto taskDto) {

        TaskDto newTaskDto = taskService.create(taskDto);

        return  new ResponseEntity<>(newTaskDto, HttpStatus.CREATED);
    }

    @PostMapping("/api/task/edit/{id}")
    public ResponseEntity<TaskDto> update(@PathVariable("id") Long id, @RequestBody TaskDto taskDto) {

        TaskDto updateTaskDto = taskService.update(id, taskDto);

        return  new ResponseEntity<>(updateTaskDto, HttpStatus.OK);
    }

    @DeleteMapping("/api/task/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteById(@PathVariable("id") Long id) {

        taskService.deleteById(id);

    }

}
