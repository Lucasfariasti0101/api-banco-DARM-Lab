package com.darm.apibanco.repository;

import com.darm.apibanco.model.Cliente;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public interface ClienteRepository extends JpaRepository<Cliente, UUID>{
    @Query(nativeQuery = true, value = "SELECT * FROM cliente " +
            "WHERE cpf LIKE (:cpf)")
    Optional<Cliente> findByCpf(String cpf);

    @Query(nativeQuery = true, value = "SELECT * FROM cliente " +
            "WHERE nome LIKE (:nome)")
    Optional<Cliente> findByNome(String nome);
}
