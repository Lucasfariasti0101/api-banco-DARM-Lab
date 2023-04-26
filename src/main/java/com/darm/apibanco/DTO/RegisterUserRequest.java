package com.darm.apibanco.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterUserRequest(
        @NotBlank @Email String email,
        @NotBlank String password,
        @NotNull RegisterPersonRequest personRequest
        ) {
}
