package com.darm.apibanco.DTO;

import com.darm.apibanco.model.Card;
import com.darm.apibanco.model.enums.SolicitationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

public record SolicitationResponse(
        @JsonProperty("solicitation-id") Long id,

        Card card,

        @JsonProperty("solicitation-message")
        String message,

        @JsonProperty("solicitation-status")
        SolicitationStatus status) {
}
