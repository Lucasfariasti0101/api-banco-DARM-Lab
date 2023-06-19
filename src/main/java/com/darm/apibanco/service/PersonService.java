package com.darm.apibanco.service;

import com.darm.apibanco.DTO.*;
import com.darm.apibanco.DTO.mapper.address.AddressRequestMapper;
import com.darm.apibanco.DTO.mapper.address.AddressResponseMapper;
import com.darm.apibanco.DTO.mapper.person.PersonResponseMapper;
import com.darm.apibanco.exception.BadRequestException;
import com.darm.apibanco.exception.ConflictException;
import com.darm.apibanco.exception.PersonNotFoundException;
import com.darm.apibanco.exception.ResourceNotFoundException;
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

    private final AddressResponseMapper addressResponseMapper;

    private final AccountService accountService;

    private final AddressRepository addressRepository;

    public PersonService(PersonRepository personRepository,
                         PersonResponseMapper mapper,
                         AddressRequestMapper addressMapper, AddressResponseMapper addressResponseMapper, AccountService accountService, AddressRepository addressRepository) {
        this.personRepository = personRepository;
        this.mapper = mapper;
        this.addressMapper = addressMapper;
        this.addressResponseMapper = addressResponseMapper;
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

    public List<AddressResponse> addAddress(Long id, List<AddressRequest> addressRequestDTO) {
        Person person = personRepository.findById(id)
                .orElseThrow(PersonNotFoundException::new);

        List<Address> addresses = addressRequestDTO.stream()
                .peek(a -> this.validateState(a.state()))
                .map(addressMapper::map)
                .peek(address -> address.setState(address.getState().toUpperCase()))
                .peek(address -> address.setPerson(person))
                .map(addressRepository::save)
                .toList();

        return addresses.stream()
                .map(addressResponseMapper::map)
                .toList();
    }

    public Address findAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(""));
    }

    public Address updateAddress(Long personId, Long addressId, AddressRequest updateRequest) {
        Address address = getPersonAddress(addressId, personId);
        return addressRepository.save(updateAddressEntity(address, updateRequest));
    }

    public void deleteAddress(Long id, Long personId) {
        Address address = getPersonAddress(id, personId);
        addressRepository.deleteById(address.getId());
    }

    private Address getPersonAddress(Long addressId, Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(PersonNotFoundException::new);

        return person.getAddress()
                .stream()
                .filter(a -> a.getId().equals(addressId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Address not found or does not belong to person."));
    }

    private Address updateAddressEntity(Address oldAddress, AddressRequest updateRequestDTO) {
        oldAddress.setState(updateRequestDTO.state());
        oldAddress.setStreet(updateRequestDTO.street());
        oldAddress.setComplement(updateRequestDTO.complement());
        oldAddress.setMunicipality(updateRequestDTO.municipality());
        oldAddress.setNumber(updateRequestDTO.number());
        oldAddress.setNeighborhood(updateRequestDTO.neighborhood());
        return oldAddress;
    }

    private void validateState(String state) {
        if (!state.matches("[a-zA-Z]{2}")) {
            throw new BadRequestException("The State field must consist of your 2-character abbreviation");
        }
    }
}