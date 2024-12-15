package com.example.TaskManagementSystem.dto.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDtoSignInResponse {

    private final String username;

    private final List<String> roles;

    private final String jwtToken;

}
