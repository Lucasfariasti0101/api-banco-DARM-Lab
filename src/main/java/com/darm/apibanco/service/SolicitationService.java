package com.darm.apibanco.service;

import com.darm.apibanco.DTO.SolicitationResponse;
import com.darm.apibanco.DTO.mapper.solicitation.SolicitationResponseMapper;
import com.darm.apibanco.model.Card;
import com.darm.apibanco.model.CardSolicitation;
import com.darm.apibanco.model.enums.CardStatus;
import com.darm.apibanco.model.enums.SolicitationStatus;
import com.darm.apibanco.repository.CardRepository;
import com.darm.apibanco.repository.SolicitationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitationService {

    private final SolicitationRepository solicitationRepository;
    private final CardRepository cardRepository;

    private final SolicitationResponseMapper mapper;

    public SolicitationService(SolicitationRepository solicitationRepository, CardRepository cardRepository, SolicitationResponseMapper mapper) {
        this.solicitationRepository = solicitationRepository;
        this.cardRepository = cardRepository;
        this.mapper = mapper;
    }

    @Transactional
    public void createSolicitation(Card card) throws RuntimeException {

        if (!card.getStatus().equals(CardStatus.PENDING))
            throw new RuntimeException("Not Pending");

        CardSolicitation solicitation = new CardSolicitation();

        solicitation.setCard(card);
        solicitation.setStatus(SolicitationStatus.PENDING);
        solicitationRepository.save(solicitation);

    }

    @Transactional
    public void approveCardSolicitation(Long solicitationId) {
        CardSolicitation solicitation = solicitationRepository.findById(solicitationId)
                .orElseThrow(() -> new RuntimeException("Solicitation not found"));

        if (!solicitation.getStatus().equals(SolicitationStatus.PENDING)) {
            throw new RuntimeException("The solicitation is not pending");
        }

        solicitation.setStatus(SolicitationStatus.APPROVED);

        Card card = solicitation.getCard();
        card.setStatus(CardStatus.APPROVED);

        cardRepository.save(card);
    }

    public List<SolicitationResponse> findAllSolicitations() {
        return solicitationRepository.findAll()
                .stream()
                .map(mapper::map)
                .toList();
    }

}
