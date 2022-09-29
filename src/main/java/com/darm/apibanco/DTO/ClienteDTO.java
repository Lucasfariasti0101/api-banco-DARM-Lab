package com.darm.apibanco.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

    private String nome;

    private String cpf;

    private String email;

    private String telefone;

    private String endereco;

    private String estado;

    private String tipoDaConta;

    private String senha;

    private String confirSenha;

}
