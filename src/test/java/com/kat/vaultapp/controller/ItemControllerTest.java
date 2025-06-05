package com.kat.vaultapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kat.vaultapp.dto.item.ItemDto;
import com.kat.vaultapp.dto.item.ItemRequestDto;
import com.kat.vaultapp.security.JwtUtil;
import com.kat.vaultapp.service.UserService;
import com.kat.vaultapp.service.implementation.ItemServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.List;
import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class ItemControllerTest {

    private static MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private JwtUtil jwtUtil;

    @MockitoBean
    private ItemServiceImpl itemService;

    @MockitoBean
    private UserService userService;

    private static List<ItemDto> mockItems;

    @BeforeAll
    static void setup(@Autowired WebApplicationContext appContext) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(appContext)
                .apply(springSecurity())
                .build();
        mockItems = List.of(
                new ItemDto(UUID.randomUUID(), "Item 1", UUID.randomUUID()),
                new ItemDto(UUID.randomUUID(), "Item 2", UUID.randomUUID())
        );
    }

    @Test
    @WithMockUser(roles = "USER")
    void test_should_getAllItems() throws Exception {
        Mockito.when(itemService.findAll()).thenReturn(mockItems);

        mockMvc.perform(get("/items")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(mockItems.size()))
                .andExpect(jsonPath("$[0].name").value("Item 1"))
                .andExpect(jsonPath("$[1].name").value("Item 2"));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "USER")
    void test_should_create_item() throws Exception {
        ItemRequestDto requestDto = new ItemRequestDto("New Item", UUID.randomUUID());
        ItemDto responseDto = new ItemDto(UUID.randomUUID(), "New Item", UUID.randomUUID());

        Mockito.when(itemService.save(any(ItemRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/items")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Item"));
    }

    @Test
    void test_should_return_unauthorized_when_creating_item_without_login_in() throws Exception {
        // given
        ItemRequestDto requestDto = new ItemRequestDto("New Item", UUID.randomUUID());

        // when & then
        mockMvc.perform(post("/items")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnauthorized());
    }
}
