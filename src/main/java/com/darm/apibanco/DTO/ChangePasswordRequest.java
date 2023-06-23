package com.darm.apibanco.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record ChangePasswordRequest(@NotEmpty @NotBlank String password,
                                    @NotBlank @NotEmpty @Email String email) {
}