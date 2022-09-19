package com.darm.apibanco.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Cliente {

    @Id
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

    @NotBlank
    private TipoDeConta tipoDaConta;

    @NotBlank
    private String senha;

    private String urlFoto;

    @OneToMany
    private List<Cartao> cartaos;
}
