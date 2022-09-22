package com.darm.apibanco.DTO;

import com.darm.apibanco.model.TipoDeCartao;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
@ToString
@Getter
public class CartaoDTO {

    private String numero;

    private String bandeira;

    private String cvc;

    private String tipoDeCartao;

    private int anosDeValidade;

    private String nomeCliente;
}
