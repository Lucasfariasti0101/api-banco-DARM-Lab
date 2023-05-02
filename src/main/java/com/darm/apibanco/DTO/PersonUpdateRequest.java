package com.darm.apibanco.DTO;

import jakarta.validation.constraints.NotBlank;

public record PersonUpdateRequest(@NotBlank String firstName, @NotBlank String LastName) {
}
