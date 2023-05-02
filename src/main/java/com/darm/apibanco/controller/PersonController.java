package com.darm.apibanco.controller;

import com.darm.apibanco.DTO.CardSimpleResponse;
import com.darm.apibanco.DTO.PersonResponse;
import com.darm.apibanco.DTO.PersonUpdateRequest;
import com.darm.apibanco.service.PersonService;
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

    @GetMapping("/cards/{id}")
    public ResponseEntity<List<CardSimpleResponse>> findCardsByPersonId(@PathVariable Long id) {
        return ResponseEntity.ok(personService.findCards(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> update(@PathVariable Long id, @RequestBody PersonUpdateRequest request) {
        return ResponseEntity.ok(personService.update(id, request));
    }
}
