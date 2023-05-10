package com.darm.apibanco.repository;

import com.darm.apibanco.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
