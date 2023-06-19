package com.darm.apibanco.controller;

import com.darm.apibanco.DTO.*;
import com.darm.apibanco.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(personService.findAll(pageable ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> findPersonById(@PathVariable Long id) {
       return ResponseEntity.ok(personService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> update(@PathVariable Long id, @RequestBody @Valid PersonUpdateRequest request) {
        return ResponseEntity.ok(personService.update(id, request));
    }

    @PutMapping("/account/{personId}")
    public ResponseEntity<PersonResponse> updateAccount(@PathVariable Long personId, @RequestBody @Valid AccountRequest request) {
        return ResponseEntity.ok(personService.updateAccount(personId, request));
    }

    @PostMapping("/add/address/{id}")
    public ResponseEntity<List<AddressResponse>> addAddress(@PathVariable Long id, @RequestBody @Valid List<AddressRequest> addressRequestDTO) {
        return ResponseEntity.ok(personService.addAddress(id, addressRequestDTO));
    }

}
