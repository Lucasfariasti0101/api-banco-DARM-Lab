package com.darm.apibanco.repository;

import com.darm.apibanco.model.CardSolicitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitationRepository extends JpaRepository<CardSolicitation, Long> {
    List<CardSolicitation> findAllByPersonId(Long id);
}
