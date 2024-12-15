package com.example.TaskManagementSystem.services.impl;

import com.example.TaskManagementSystem.dto.security.UserDtoSignIn;
import com.example.TaskManagementSystem.dto.security.UserDtoSignInResponse;
import com.example.TaskManagementSystem.dto.security.UserDtoSignUp;
import com.example.TaskManagementSystem.dto.security.UserDtoSignUpResponse;
import com.example.TaskManagementSystem.models.User;
import com.example.TaskManagementSystem.models.enums.Role;
import com.example.TaskManagementSystem.models.security.CustomUserDetails;
import com.example.TaskManagementSystem.repository.security.UserRepository;
import com.example.TaskManagementSystem.security.jwt.JwtUtils;
import com.example.TaskManagementSystem.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtUtils jwtUtils;

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;

    @Transactional
    @Override
    public UserDtoSignInResponse authenticateUser(UserDtoSignIn userDtoSignIn) {

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDtoSignIn.getUsername(),
                        userDtoSignIn.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new UserDtoSignInResponse(userDetails.getUsername(),
                roles, jwtToken);

    }


    @Transactional
    @Override
    public UserDtoSignUpResponse registerUser(UserDtoSignUp userDtoSignUp) {

        if (userRepository.existsByUsername(userDtoSignUp.getUsername())) {
            return new UserDtoSignUpResponse("This username is busy");
        }

        User user = new User(null, userDtoSignUp.getUsername(),
                userDtoSignUp.getName(),
                encoder.encode(userDtoSignUp.getPassword()), Role.ROLE_USER);

        userRepository.save(user);

        return new UserDtoSignUpResponse("The user was created successfully");

    }
}
