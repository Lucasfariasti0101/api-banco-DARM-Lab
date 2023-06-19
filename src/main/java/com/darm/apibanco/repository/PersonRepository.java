package com.darm.apibanco.repository;

import com.darm.apibanco.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    boolean existsByCpf(String cpf);
}
