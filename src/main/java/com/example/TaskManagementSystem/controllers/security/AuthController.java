package com.example.TaskManagementSystem.controllers.security;

import com.example.TaskManagementSystem.dto.security.UserDtoSignIn;
import com.example.TaskManagementSystem.dto.security.UserDtoSignInResponse;
import com.example.TaskManagementSystem.dto.security.UserDtoSignUp;
import com.example.TaskManagementSystem.dto.security.UserDtoSignUpResponse;
import com.example.TaskManagementSystem.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Регистрация и аутентификация", description = "Регистрирует и производит аутентификацию")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @Operation(summary = "Аутентификация пользователя")
    @PostMapping("/public/signin")
    public ResponseEntity<UserDtoSignInResponse> authenticateUser(@Valid @RequestBody UserDtoSignIn userDtoSignIn) {
        return ResponseEntity.ok(userService.authenticateUser(userDtoSignIn));
    }

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/public/signup")
    public ResponseEntity<UserDtoSignUpResponse> registerUser(@Valid @RequestBody UserDtoSignUp userDtoSignUp) {
        return ResponseEntity.ok(userService.registerUser(userDtoSignUp));
    }
}
