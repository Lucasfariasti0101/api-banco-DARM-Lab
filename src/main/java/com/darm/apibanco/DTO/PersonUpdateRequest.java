package com.darm.apibanco.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record PersonUpdateRequest(@NotBlank @NotEmpty String firstName, @NotBlank @NotEmpty String LastName) {
}
