package com.kat.vaultapp.dto.item;

import java.util.UUID;

public record ItemRequestDto(
        String name,
        UUID userId
) {
}
