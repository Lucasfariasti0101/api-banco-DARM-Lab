package com.darm.apibanco.repository;

import com.darm.apibanco.model.CardSolicitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SolicitationRepository extends JpaRepository<CardSolicitation, Long> {
    List<CardSolicitation> findAllByPersonId(Long id);

    //TODO: Implementar uma query para buscar uma lista de solicitações pelo Estado de person.
    @Query()
    List<CardSolicitation> findAllByState(String state);
}
