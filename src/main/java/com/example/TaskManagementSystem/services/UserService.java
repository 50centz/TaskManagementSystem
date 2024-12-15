package com.example.TaskManagementSystem.services;

import com.example.TaskManagementSystem.dto.security.UserDtoSignIn;
import com.example.TaskManagementSystem.dto.security.UserDtoSignInResponse;
import com.example.TaskManagementSystem.dto.security.UserDtoSignUp;
import com.example.TaskManagementSystem.dto.security.UserDtoSignUpResponse;

public interface UserService {

    UserDtoSignInResponse authenticateUser(UserDtoSignIn userDtoSignIn);

    UserDtoSignUpResponse registerUser(UserDtoSignUp userDtoSignUp);
}
