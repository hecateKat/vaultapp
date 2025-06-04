package com.kat.vaultapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kat.vaultapp.dto.user.UserLoginRequestDto;
import com.kat.vaultapp.dto.user.UserLoginResponseDto;
import com.kat.vaultapp.dto.user.UserRegistrationRequestDto;
import com.kat.vaultapp.dto.user.UserResponseDto;
import com.kat.vaultapp.exception.RegistrationException;
import com.kat.vaultapp.security.AuthenticationService;
import com.kat.vaultapp.security.JwtAuthenticationFilter;
import com.kat.vaultapp.security.JwtUtil;
import com.kat.vaultapp.service.UserService;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.io.IOException;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthenticationController.class)
class AuthenticationControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockitoBean
//    private UserService userService;
//
//    @MockitoBean
//    private JwtUtil jwtUtil;
//
//    @MockitoBean
//    private JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    @MockitoBean
//    private AuthenticationService authenticationService;
//
//    @BeforeEach
//    void setUp() throws ServletException, IOException {
//        Mockito.doNothing().when(jwtAuthenticationFilter).doFilter(any(), any(), any());
//    }
//
//    @Test
//    void test_should_register_user_successfully() throws Exception {
//        // given
//        UserRegistrationRequestDto requestDto = new UserRegistrationRequestDto("testuser", "password", "password");
//        UserResponseDto responseDto = new UserResponseDto(UUID.randomUUID(), "testuser");
//
//        Mockito.when(userService.register(any(UserRegistrationRequestDto.class))).thenReturn(responseDto);
//
//        // when & then
//        mockMvc.perform(post("/auth/register")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(requestDto)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.username").value("testuser"));
//    }
//
//    @Test
//    void test_should_return_bad_request_when_registration_fails() throws Exception {
//        // given
//        UserRegistrationRequestDto requestDto = new UserRegistrationRequestDto("", "password", "password");
//
//        Mockito.when(userService.register(any(UserRegistrationRequestDto.class)))
//                .thenThrow(new RegistrationException("Invalid data"));
//
//        // when & then
//        mockMvc.perform(post("/auth/register")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(requestDto)))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.message").value("Invalid data"));
//    }
//
//    @Test
//    void test_should_login_user_successfully() throws Exception {
//        // given
//        UserLoginRequestDto requestDto = new UserLoginRequestDto("testuser", "password");
//        UserLoginResponseDto responseDto = new UserLoginResponseDto("token123");
//
//        Mockito.when(authenticationService.isAuthenticated(any(UserLoginRequestDto.class))).thenReturn(responseDto);
//
//        // when & then
//        mockMvc.perform(post("/auth/login")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(requestDto)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.token").value("token123"));
//    }
//
//    @Test
//    void test_should_return_unauthorized_when_login_fails() throws Exception {
//        // given
//        UserLoginRequestDto requestDto = new UserLoginRequestDto("testuser", "wrongpassword");
//
//        Mockito.when(authenticationService.isAuthenticated(any(UserLoginRequestDto.class)))
//                .thenThrow(new RuntimeException("Invalid credentials"));
//
//        // when & then
//        mockMvc.perform(post("/auth/login")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(requestDto)))
//                .andExpect(status().isUnauthorized());
//    }
}
