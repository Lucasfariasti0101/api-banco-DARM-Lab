package com.darm.apibanco.service;

import com.darm.apibanco.DTO.CardRequest;
import com.darm.apibanco.model.Card;
import com.darm.apibanco.model.Person;
import com.darm.apibanco.model.enums.CardStatus;
import com.darm.apibanco.model.enums.CardType;
import com.darm.apibanco.repository.CardRepository;
import com.darm.apibanco.repository.PersonRepository;
import com.darm.apibanco.util.CardNumberValidateUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final PersonRepository personRepository;

    public CardService(CardRepository cardRepository, PersonRepository personRepository) {
        this.cardRepository = cardRepository;
        this.personRepository = personRepository;
    }

    public Card findCardById(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found"));
    }

    public Card save(Long personId, CardRequest request) {

        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Person not found"));

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

        return cardRepository.save(card);

    }

}
