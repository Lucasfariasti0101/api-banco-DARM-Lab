package com.darm.apibanco.model.enums;

import com.darm.apibanco.exception.BadRequestException;

import java.util.Arrays;

public enum CardType {
    DEBIT, CREDIT, SAVINGS, CURRENT_SAVING, CREDIT_SAVINGS;

    public static CardType convertTo(String value) {
        return Arrays.stream(CardType.values()).filter(ct -> ct.name()
                .equals(value.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Illegal value for CardType"));
    }
}
