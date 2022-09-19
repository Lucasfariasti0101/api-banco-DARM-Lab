package com.darm.apibanco.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, unique = true, nullable = false)
    private UUID idCartao;

    @NotBlank
    @Size(max = 19)
    private String numero;

    @NotBlank
    @Size(max = 20)
    private String bandeira;

    @NotBlank
    @Size(max = 3)
    private String cvc;

    @NotBlank
    private TipoDeCartao tipoDeCartao;

    @NotBlank
    private LocalDate dataDeValidade;

    public void data (int qtdAnos, LocalDate localDate) {
        dataDeValidade = LocalDate.now().plusYears(qtdAnos);
        setDataDeValidade(dataDeValidade);
    }

}
