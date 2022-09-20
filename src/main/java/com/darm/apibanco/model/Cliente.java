package com.darm.apibanco.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID id;

    @NotBlank
    @Size(max = 60)
    private String nome;

    @NotBlank
    @Size(max = 11)
    private String cpf;

    @NotBlank
    @Size(max = 255)
    @Email
    private String email;

    @NotBlank
    @Size(max = 20)
    private String telefone;

    @NotBlank
    @Size(max = 255)
    private String endereco;

    @Enumerated(EnumType.STRING)
    private TipoDeConta tipoDaConta;

    @NotBlank
    private String senha;

    private String urlFoto;

    @OneToMany
    private List<Cartao> cartaos;
}
