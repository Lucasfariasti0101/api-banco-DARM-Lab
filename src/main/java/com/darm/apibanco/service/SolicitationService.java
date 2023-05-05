package com.darm.apibanco.service;

import com.darm.apibanco.model.Card;
import com.darm.apibanco.model.CardSolicitation;
import com.darm.apibanco.model.enums.CardStatus;
import com.darm.apibanco.model.enums.SolicitationStatus;
import com.darm.apibanco.repository.CardRepository;
import com.darm.apibanco.repository.SolicitationRepository;
import org.springframework.stereotype.Service;

@Service
public class solicitationService {

    private final SolicitationRepository solicitationRepository;
    private final CardRepository cardRepository;

    public solicitationService(SolicitationRepository solicitationRepository, CardRepository cardRepository) {
        this.solicitationRepository = solicitationRepository;
        this.cardRepository = cardRepository;
    }

    public void createSolicitation(Card card) throws RuntimeException {

        if (!card.getStatus().equals(CardStatus.PENDING))
            throw new RuntimeException("Not Pending");

        CardSolicitation solicitation = new CardSolicitation();

        solicitation.setCard(card);
        solicitation.setStatus(SolicitationStatus.PENDING);
        solicitationRepository.save(solicitation);

    }

    public Card approveCardSolicitation(Long solicitationId) {
        CardSolicitation solicitation = solicitationRepository.findById(solicitationId)
                .orElseThrow(() -> new RuntimeException("Solicitation not found"));

        if (!solicitation.getStatus().equals(SolicitationStatus.PENDING)) {
            throw new RuntimeException("The solicitation is not pending");
        }

        solicitation.setStatus(SolicitationStatus.APPROVED);

        Card card = solicitation.getCard();
        card.setStatus(CardStatus.APPROVED);

        return cardRepository.save(card);
    }

}
