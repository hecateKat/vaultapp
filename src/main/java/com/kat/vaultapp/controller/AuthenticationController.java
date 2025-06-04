package com.kat.vaultapp.controller;

import com.kat.vaultapp.dto.user.UserLoginRequestDto;
import com.kat.vaultapp.dto.user.UserLoginResponseDto;
import com.kat.vaultapp.dto.user.UserRegistrationRequestDto;
import com.kat.vaultapp.dto.user.UserResponseDto;
import com.kat.vaultapp.exception.RegistrationException;
import com.kat.vaultapp.security.AuthenticationService;
import com.kat.vaultapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public UserResponseDto registerUser(
            @RequestBody @Valid UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        return userService.register(requestDto);
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto loginRequestDto) {
        return authenticationService.isAuthenticated(loginRequestDto);
    }
}
