package com.darm.apibanco.controller;

import com.darm.apibanco.DTO.ClienteDTO;
import com.darm.apibanco.model.Cliente;
import com.darm.apibanco.model.TipoDeConta;
import com.darm.apibanco.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @PostMapping("/cadastar-usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEndereco(clienteDTO.getEndereco());

        if(clienteDTO.getTipoDaConta().equals("Corrente")) {
            cliente.setTipoDaConta(TipoDeConta.CORRENTE);
        }

        if(clienteDTO.getTipoDaConta().equals("Poupança")) {
            cliente.setTipoDaConta(TipoDeConta.POUPANCA);
        }

        try {
            if (clienteDTO.getSenha().equals(clienteDTO.getConfirSenha())) {
                cliente.setSenha(clienteDTO.getSenha());
            }
        } catch (Exception e) {
            System.out.println("Senhas não são correspondêntes");
            e.printStackTrace();
        }

        return clienteRepository.save(cliente);
    }


}
