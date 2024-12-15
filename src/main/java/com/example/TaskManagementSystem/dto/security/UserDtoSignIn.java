package com.example.TaskManagementSystem.dto.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoSignIn {


    @Email(message = "The email address is entered incorrectly")
    private String username;

    @Size(min = 8, max = 20, message = "The password must contain at least 8 characters")
    private String password;

}