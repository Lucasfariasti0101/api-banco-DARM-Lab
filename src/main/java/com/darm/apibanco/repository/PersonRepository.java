package com.darm.apibanco.repository;

import com.darm.apibanco.model.Card;
import com.darm.apibanco.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
