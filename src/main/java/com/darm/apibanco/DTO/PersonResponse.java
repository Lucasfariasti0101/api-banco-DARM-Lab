package com.darm.apibanco.DTO;

import com.darm.apibanco.model.Address;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PersonResponse(
        @JsonProperty("person-id")
        Long id,

        @JsonProperty("first-name")
        String firstName,

        @JsonProperty("last-name")
        String lastName,

        String cpf,

        @JsonProperty("phone-number")
        String phoneNumber,
        List<Address> address,

        @JsonProperty("account-number")
        String number,

        @JsonProperty("account-type")
        String accountType
) {
}
