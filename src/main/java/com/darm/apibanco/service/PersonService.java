package com.darm.apibanco.service;

import com.darm.apibanco.DTO.AccountRequest;
import com.darm.apibanco.DTO.AddressRequest;
import com.darm.apibanco.DTO.PersonResponse;
import com.darm.apibanco.DTO.PersonUpdateRequest;
import com.darm.apibanco.DTO.mapper.address.AddressRequestMapper;
import com.darm.apibanco.DTO.mapper.person.PersonResponseMapper;
import com.darm.apibanco.exception.ConflictException;
import com.darm.apibanco.exception.PersonNotFoundException;
import com.darm.apibanco.model.Address;
import com.darm.apibanco.model.Person;
import com.darm.apibanco.repository.AddressRepository;
import com.darm.apibanco.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonResponseMapper mapper;

    private final AddressRequestMapper addressMapper;

    private final AccountService accountService;

    private final AddressRepository addressRepository;

    public PersonService(PersonRepository personRepository,
                         PersonResponseMapper mapper,
                         AddressRequestMapper addressMapper, AccountService accountService, AddressRepository addressRepository) {
        this.personRepository = personRepository;
        this.mapper = mapper;
        this.addressMapper = addressMapper;
        this.accountService = accountService;
        this.addressRepository = addressRepository;
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

    public PersonResponse updateAccount(Long id, AccountRequest request) {
        accountService.updateAccountType(id, request);
        return this.findById(id);
    }

    public void addAddress(Long id, List<AddressRequest> addressRequestDTO) {
        Person person = personRepository.findById(id)
                .orElseThrow(PersonNotFoundException::new);

        List<Address> addresses = addressRequestDTO.stream()
                .map(addressMapper::map)
                .peek(address -> address.setPerson(person))
                .map(addressRepository::save)
                .toList();

        person.setAddress(addresses);
        personRepository.save(person);
    }
}
