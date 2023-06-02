package com.darm.apibanco.service;

import com.darm.apibanco.DTO.AccountRequest;
import com.darm.apibanco.DTO.PersonResponse;
import com.darm.apibanco.DTO.PersonUpdateRequest;
import com.darm.apibanco.DTO.mapper.person.PersonResponseMapper;
import com.darm.apibanco.exception.ConflictException;
import com.darm.apibanco.exception.PersonNotFoundException;
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

    private final AccountService accountService;

    public PersonService(PersonRepository personRepository,
                         PersonResponseMapper mapper,
                         AccountService accountService) {
        this.personRepository = personRepository;
        this.mapper = mapper;
        this.accountService = accountService;
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

    @Transactional
    public Person save(Person person) {
        boolean personExists = personRepository.existsByCpf(person.getCpf());
        if (!personExists) {
            throw new ConflictException("The person cpf is already being used.");
        }
        Person personSaved = personRepository.save(person);

        AccountRequest request = new AccountRequest("Debit");

        accountService.save(personSaved.getId(), request);

        return personSaved;
    }

}
