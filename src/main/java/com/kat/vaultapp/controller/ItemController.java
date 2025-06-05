package com.kat.vaultapp.controller;

import com.kat.vaultapp.dto.item.ItemDto;
import com.kat.vaultapp.dto.item.ItemRequestDto;
import com.kat.vaultapp.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping
    @Operation(summary = "Create an item",
    description = "Create a new item")
    public ItemDto createItem(@RequestBody @Valid ItemRequestDto requestDto,
                              @AuthenticationPrincipal(expression = "username") String username) {
        return itemService.save(requestDto, username);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    @Operation(summary = "Get all items",
    description = "Get a list of current user's items")
    public List<ItemDto> getAllItems(@AuthenticationPrincipal(expression = "username") String username) {
        return itemService.findAllByUsername(username);
    }
}
