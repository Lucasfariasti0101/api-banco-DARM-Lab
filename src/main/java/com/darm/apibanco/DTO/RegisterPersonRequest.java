package com.darm.apibanco.DTO;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record RegisterPersonRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank @CPF String cpf,
        @NotBlank String phoneNumber
) {
}
