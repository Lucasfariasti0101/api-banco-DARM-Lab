package com.darm.apibanco.DTO;

import jakarta.validation.constraints.NotBlank;

public record AccountRequest(@NotBlank String accountType) {
}
