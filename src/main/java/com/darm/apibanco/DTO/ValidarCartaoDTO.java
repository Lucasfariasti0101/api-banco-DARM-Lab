package com.darm.apibanco.DTO;

import lombok.Getter;

@Getter
public class ValidarCartaoDTO {
    private String num;
    private String cvc;
    private int status;
}
