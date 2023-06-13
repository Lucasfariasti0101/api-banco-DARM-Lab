package com.darm.apibanco.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AddressRequest(@NotBlank String municipality,
                             @NotBlank String street,
                             @NotBlank String number,
                             @NotBlank String complement,
                             @NotBlank String neighborhood,
                             @NotBlank @Size(max = 2, min = 2) String state) {
}
