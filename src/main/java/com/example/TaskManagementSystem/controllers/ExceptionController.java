package com.example.TaskManagementSystem.controllers;

import com.example.TaskManagementSystem.dto.ErrorDto;
import com.example.TaskManagementSystem.exeptions.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> authenticationException(AuthenticationException exception) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", "Bad credentials");
        map.put("status", false);
        return new ResponseEntity<Object>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> errorMessages = new ArrayList<>();
        exception.getAllErrors().forEach(err -> {
            errorMessages.add(err.getDefaultMessage());
        });

        return new ResponseEntity<ErrorDto>(new ErrorDto(errorMessages.getFirst()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> entityNotFoundException(EntityNotFoundException exception) {

        return new ResponseEntity<ErrorDto>(new ErrorDto(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> constraintViolationException(ConstraintViolationException exception) {

        return new ResponseEntity<ErrorDto>(new ErrorDto(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }


}
