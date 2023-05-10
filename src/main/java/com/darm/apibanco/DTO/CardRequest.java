package com.darm.apibanco.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CardRequest(
       @NotBlank @Size(min = 16, max = 19) String number,
       @NotBlank String flagFinance,
       @NotBlank @Size(min = 3, max = 3) String cvv,
       @NotBlank String cardType,
       @NotNull int yearsToValidity
) {
}
