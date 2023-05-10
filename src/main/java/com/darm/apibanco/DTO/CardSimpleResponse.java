package com.darm.apibanco.DTO;

import com.darm.apibanco.model.enums.CardStatus;
import com.darm.apibanco.model.enums.CardType;

import java.time.LocalDate;

public record CardSimpleResponse(
        String number,

        String CVV,

        String flagFinance,

        LocalDate expirationDate,

        CardType type,

        CardStatus status) {

}
