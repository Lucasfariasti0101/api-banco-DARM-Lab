package com.darm.apibanco.DTO;

import jakarta.validation.constraints.NotEmpty;

public record ForgotPasswordRequest(@NotEmpty String email) {
}
