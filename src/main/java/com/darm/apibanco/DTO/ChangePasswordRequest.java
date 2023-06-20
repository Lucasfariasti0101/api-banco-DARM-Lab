package com.darm.apibanco.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record ChangePasswordRequest(@NotEmpty @NotBlank String password) {
}
