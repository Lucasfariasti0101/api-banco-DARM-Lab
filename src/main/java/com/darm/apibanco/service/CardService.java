package com.darm.apibanco.service;

import com.darm.apibanco.DTO.CardRequest;
import com.darm.apibanco.DTO.CardSimpleResponse;
import com.darm.apibanco.DTO.mapper.card.CardSimpleResponseMapper;
import com.darm.apibanco.exception.BadRequestException;
import com.darm.apibanco.exception.PersonNotFoundException;
import com.darm.apibanco.exception.ResourceNotFoundException;
import com.darm.apibanco.model.Card;
import com.darm.apibanco.model.Person;
import com.darm.apibanco.model.enums.CardStatus;
import com.darm.apibanco.model.enums.CardType;
import com.darm.apibanco.repository.CardRepository;
import com.darm.apibanco.repository.PersonRepository;
import com.darm.apibanco.util.CardNumberValidateUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final PersonRepository personRepository;

    private final SolicitationService solicitationService;

    private final CardSimpleResponseMapper cardSimpleResponseMapper;

    public CardService(CardRepository cardRepository, PersonRepository personRepository, SolicitationService solicitationService, CardSimpleResponseMapper cardSimpleResponseMapper) {
        this.cardRepository = cardRepository;
        this.personRepository = personRepository;
        this.solicitationService = solicitationService;
        this.cardSimpleResponseMapper = cardSimpleResponseMapper;
    }

    public Card findCardById(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found"));
    }

    @Transactional
    public Card save(Long personId, CardRequest request) {

        Person person = personRepository.findById(personId)
                .orElseThrow(PersonNotFoundException::new);

        if (person.getCards().size() > 6)
            throw new BadRequestException("The amount of 6 registered cards has been reached");

        Card card = new Card();

        card.setCVV(request.cvv());
        card.setType(CardType.convertTo(request.cardType()));
        card.setStatus(CardStatus.PENDING);

        String flag = CardNumberValidateUtil
                .validateAndReturnFlag(request.number());

        if (!flag.equals(request.flagFinance().toUpperCase())) {
            throw new RuntimeException("");
        }

        card.setNumber(CardNumberValidateUtil.formatNumber(request.number()));
        card.setFlagFinance(flag);
        card.setExpirationDate(LocalDate.now().plusYears(request.yearsToValidity()));
        card.setPerson(person);

        Card cardSaved = cardRepository.save(card);

        solicitationService.createSolicitation(cardSaved);
        return cardSaved;
    }

    public List<CardSimpleResponse> findCards(Long id) {
        return cardRepository.findAll()
                .stream()
                .map(cardSimpleResponseMapper::map)
                .toList();
    }

    public void approveSolicitation(Long solicitationId) {
        solicitationService.approveCardSolicitation(solicitationId);
    }

}
