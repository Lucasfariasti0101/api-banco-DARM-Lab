package com.darm.apibanco.repository;

import com.darm.apibanco.model.Administrador;
import com.darm.apibanco.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AdmRepository extends JpaRepository<Administrador, UUID> {

    @Query(nativeQuery = true, value = "SELECT EXISTS(" +
            " SELECT * FROM administrador" +
            " WHERE cpf LIKE (:cpf))")
     boolean findByCpf(String cpf);

    @Query(nativeQuery = true, value = "SELECT * FROM administrador" +
            " WHERE cpf LIKE (:cpf)")
    Administrador getByCpf(String cpf);

    @Query(nativeQuery = true, value = "SELECT * FROM clientes" +
            " WHERE estado LIKE (:estado)")
    List<Cliente> findAllByEstado(String estado);
}
