package com.darm.apibanco.repository;

import com.darm.apibanco.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID>{
    @Query(nativeQuery = true, value = "SELECT * FROM cliente " +
            "WHERE cpf LIKE (:cpf)")
    Optional<Cliente> findByCpf(String cpf);

    @Query(nativeQuery = true, value = "SELECT * FROM cliente " +
            "WHERE nome LIKE (:nome)")
    Optional<Cliente> findByNome(String nome);

    @Query(nativeQuery = true, value = "SELECT EXISTS(" +
            " SELECT * FROM cliente" +
            " WHERE senha LIKE (:senha)" +
            "AND cpf LIKE (:cpf))")
    boolean findSenhaAndCpf (String senha, String cpf);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM cliente " +
            "WHERE cpf LIKE (:cpf) " +
            "AND senha LIKE (:senha) " +
            "AND email LIKE (:email)")
    boolean deleteByCpfAndEmailAndSenha (String cpf, String senha, String email);

    @Query(nativeQuery = true, value = "DELETE FROM cartao " +
            "WHERE numero LIKE (:num) " +
            "AND cvc LIKE (:cvc) ")
    boolean deletarCartaoByNumCvc(String num, String cvc);

    @Query(nativeQuery = true, value = "SELECT * FROM cliente " +
            "ORDER BY nome ASC")
    List<Cliente> findAllByOrdemAlfabetica();
}
