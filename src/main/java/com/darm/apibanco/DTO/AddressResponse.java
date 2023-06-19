package com.darm.apibanco.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressResponse(
        String municipality,

        String street,

        String number,

        String complement,

        String neighborhood,
        String state,
        @JsonProperty("person-name")
        String personName,
        @JsonProperty("person-id")
        Long personId) {


}
