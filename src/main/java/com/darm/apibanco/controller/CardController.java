package com.darm.apibanco.controller;

import com.darm.apibanco.DTO.CardRequest;
import com.darm.apibanco.DTO.CardSimpleResponse;
import com.darm.apibanco.model.Card;
import com.darm.apibanco.service.CardService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Card> save(@PathVariable Long id, @RequestBody @Valid CardRequest request) {
        URI uri = URI.create("/api/v1/cards/");
        return ResponseEntity.created(uri).body(cardService.save(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.findCardById(id));
    }

    @GetMapping("/cards-by-person/{id}")
    public ResponseEntity<List<CardSimpleResponse>> findCardsByPersonId(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.findCards(id));
    }

}
