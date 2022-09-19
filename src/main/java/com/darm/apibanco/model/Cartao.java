package com.darm.apibanco.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Cartao {

    @Id
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
