package com.darm.apibanco.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Cliente {

    @Id
    private UUID id;

    private String nome;

    private String cpf;

    private String email;

    private String telefone;

    private String endereco;

    private TipoDeConta tipoDaConta;

    private String senha;
}
