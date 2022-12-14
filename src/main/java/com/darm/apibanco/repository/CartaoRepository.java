package com.darm.apibanco.repository;

import com.darm.apibanco.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CartaoRepository extends JpaRepository<Cartao, UUID> {
    @Query(nativeQuery = true, value = "SELECT * FROM cartao " +
            "WHERE numero LIKE (:numero)")
    Optional<Cartao> findByNum(String numero);

    @Query(nativeQuery = true, value = "SELECT * FROM cartao " +
            "WHERE numero LIKE (:num) " +
            "AND cvc LIKE (:cvc)")
    Optional<Cartao> getByNumCvc(String num, String cvc);
}
