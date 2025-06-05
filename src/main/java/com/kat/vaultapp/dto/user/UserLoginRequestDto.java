package com.kat.vaultapp.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public record UserLoginRequestDto(
        @NotBlank
        @Size(min = 8, max = 20)
        String login,
        @NotBlank
        @Length(min = 8, max = 25)
        String password) {
}
