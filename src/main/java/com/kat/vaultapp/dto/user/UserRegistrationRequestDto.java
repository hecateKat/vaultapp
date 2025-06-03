package com.kat.vaultapp.dto.user;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserRegistrationRequestDto(
        @NotBlank
        String login,
        @NotBlank
        @Length(min = 8, max = 25)
        String password,
        @NotBlank
        @Length(min = 8, max = 25)
        String repeatPassword) {}
