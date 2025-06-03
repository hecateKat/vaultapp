package com.kat.vaultapp.dto.user;

import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String login
) {
}
