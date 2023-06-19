package com.darm.apibanco.DTO;

import com.darm.apibanco.model.enums.CardStatus;
import com.darm.apibanco.model.enums.CardType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record CardSimpleResponse(
        @JsonProperty("card-number")
        String number,

        @JsonProperty("card-cvv")
        String CVV,

        @JsonProperty("flag-finance")
        String flagFinance,

        @JsonProperty("expiration-date")
        LocalDate expirationDate,

        @JsonProperty("card-type")
        CardType type,

        @JsonProperty("card-status")
        CardStatus status) {

}
