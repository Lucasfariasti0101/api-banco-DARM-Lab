package com.darm.apibanco.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

public record RegisterPersonRequest(
        @JsonProperty("first-name") @NotBlank @NotEmpty String firstName,
        @JsonProperty("last-name") @NotBlank @NotEmpty String lastName,
        @NotBlank @NotEmpty @CPF String cpf,
        @JsonProperty("phone-number") @NotBlank @NotEmpty String phoneNumber
) {
}
