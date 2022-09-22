package com.darm.apibanco.DTO;

import lombok.Getter;

@Getter
public class MudarSenhaDTO {
    private String cpf;
    private String senha;
    private String novaSenha;
}
