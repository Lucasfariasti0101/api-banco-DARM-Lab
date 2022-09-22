package com.darm.apibanco.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;
@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID id;

    @NonNull
    @NotBlank
    @Size(max = 60)
    private String nome;

    @NonNull
    @NotBlank
    @Size(max = 11)
    private String cpf;

    @NonNull
    @NotBlank
    @Size(max = 255)
    @Email
    private String email;

    @NonNull
    @NotBlank
    @Size(max = 20)
    private String telefone;

    @NonNull
    @NotBlank
    @Size(max = 255)
    private String endereco;

    @NonNull
    @Enumerated(EnumType.STRING)
    private TipoDeConta tipoDaConta;

    @NonNull
    @NotBlank
    private String senha;

    private String urlFoto;

    @OneToMany(mappedBy = "cliente")
    private List<Cartao> cartaos;

    public Cliente() {
    }
}
