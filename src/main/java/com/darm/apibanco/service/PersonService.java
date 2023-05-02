package com.darm.apibanco.service;

import com.darm.apibanco.DTO.CardSimpleResponse;
import com.darm.apibanco.DTO.PersonResponse;
import com.darm.apibanco.DTO.PersonUpdateRequest;
import com.darm.apibanco.DTO.mapper.card.CardSimpleResponseMapper;
import com.darm.apibanco.DTO.mapper.person.PersonResponseMapper;
import com.darm.apibanco.exeption.PersonNotFoundException;
import com.darm.apibanco.model.Person;
import com.darm.apibanco.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonResponseMapper mapper;

    private final CardSimpleResponseMapper cardSimpleResponseMapper;

    public PersonService(PersonRepository personRepository, PersonResponseMapper mapper, CardSimpleResponseMapper cardSimpleResponseMapper) {
        this.personRepository = personRepository;
        this.mapper = mapper;
        this.cardSimpleResponseMapper = cardSimpleResponseMapper;
    }

    public PersonResponse findById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(PersonNotFoundException::new);

        return mapper.map(person);

    }

    @Transactional
    public PersonResponse update(Long id, PersonUpdateRequest request) {
        Person person = personRepository.findById(id)
                .orElseThrow(PersonNotFoundException::new);

        person.setFirstName(request.firstName());
        person.setLastName(request.LastName());

        personRepository.save(person);
        return mapper.map(person);
    }

    public List<PersonResponse> findAll(Pageable pageable) {
        return personRepository.findAll(pageable)
                .stream()
                .map(mapper::map)
                .toList();
    }

    public List<CardSimpleResponse> findCards(Long id) {
        return personRepository.findAllCards()
                .stream()
                .map(cardSimpleResponseMapper::map)
                .toList();
    }
}
