package com.darm.apibanco.repository;

import com.darm.apibanco.model.CardSolicitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitationRepository extends JpaRepository<CardSolicitation, Long> {
}
