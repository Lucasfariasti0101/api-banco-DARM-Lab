package com.darm.apibanco.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record RegisterPersonRequest(
        @JsonProperty("first-name") @NotBlank String firstName,
        @JsonProperty("last-name") @NotBlank String lastName,
        @NotBlank @CPF String cpf,
        @JsonProperty("phone-number") @NotBlank String phoneNumber
) {
}
