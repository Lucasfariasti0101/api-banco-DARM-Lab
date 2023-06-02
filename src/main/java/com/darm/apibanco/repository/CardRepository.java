package com.darm.apibanco.repository;

import com.darm.apibanco.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByPersonId(Long id);
}
