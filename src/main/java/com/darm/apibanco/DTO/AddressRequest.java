package com.darm.apibanco.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record AddressRequest(@NotBlank @NotEmpty String municipality,
                             @NotBlank @NotEmpty String street,
                             @NotBlank @NotEmpty String number,
                             @NotBlank @NotEmpty String complement,
                             @NotBlank @NotEmpty String neighborhood,
                             @NotBlank @NotEmpty String state) {
}
