package com.darm.apibanco.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CardRequest(
       @NotBlank @NotEmpty @Size(min = 16, max = 19) String number,
       @NotBlank @NotEmpty String flagFinance,
       @NotBlank @NotEmpty @Size(min = 3, max = 3) String cvv,
       @NotBlank @NotEmpty String cardType,
       @NotNull int yearsToValidity
) {
}
