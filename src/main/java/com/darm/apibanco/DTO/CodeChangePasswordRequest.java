package com.darm.apibanco.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record CodeChangePasswordRequest(@NotBlank @NotEmpty String code,
                                        @NotBlank @NotEmpty String email) {
}
