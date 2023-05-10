package com.darm.apibanco.DTO.mapper.solicitation;

import com.darm.apibanco.DTO.SolicitationResponse;
import com.darm.apibanco.model.CardSolicitation;
import com.darm.apibanco.shared.Mapper;
import org.springframework.stereotype.Component;

@Component
public class SolicitationResponseMapper implements Mapper<CardSolicitation, SolicitationResponse> {
    @Override
    public SolicitationResponse map(CardSolicitation source) {
        return new SolicitationResponse(source.getId(),
                source.getCard(),
                source.getMessage(),
                source.getStatus());
    }
}
