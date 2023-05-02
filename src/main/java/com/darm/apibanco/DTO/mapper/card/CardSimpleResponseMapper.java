package com.darm.apibanco.DTO.mapper.card;

import com.darm.apibanco.DTO.CardSimpleResponse;
import com.darm.apibanco.model.Card;
import com.darm.apibanco.shared.Mapper;
import org.springframework.stereotype.Component;

@Component
public class CardSimpleResponseMapper implements Mapper<Card, CardSimpleResponse> {
    @Override
    public CardSimpleResponse map(Card source) {
        return new CardSimpleResponse(source.getNumber(),
                source.getCVV(),
                source.getFlagFinance(),
                source.getExpirationDate(),
                source.getType(),
                source.getStatus());
    }
}
