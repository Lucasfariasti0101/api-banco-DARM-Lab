package com.darm.apibanco.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterUserRequest(
        @NotBlank @NotEmpty @Email String email,
        @NotBlank @NotEmpty String password,
        @JsonProperty("person") @NotNull RegisterPersonRequest personRequest) {
}
