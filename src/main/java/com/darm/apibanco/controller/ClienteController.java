package com.darm.apibanco.controller;

import com.darm.apibanco.DTO.ClienteDTO;
import com.darm.apibanco.DTO.LoginDTO;
import com.darm.apibanco.DTO.ReculperarSenhaDTO;
import com.darm.apibanco.model.Cliente;
import com.darm.apibanco.model.TipoDeConta;
import com.darm.apibanco.repository.ClienteRepository;
import com.darm.apibanco.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ClienteController {


    ClienteRepository clienteRepository;
    ClienteService clienteService;

    @PostMapping("/cadastrar-usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setEndereco(clienteDTO.getEndereco());

        if (clienteDTO.getTipoDaConta().equals("Corrente")) {
            cliente.setTipoDaConta(TipoDeConta.CORRENTE);
        }

        if (clienteDTO.getTipoDaConta().equals("Poupança")) {
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

        return clienteService.save(cliente);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDTO usuario) {
        Cliente cliente;
        String cpf = usuario.getCpf();
        String nome = usuario.getNome();

        try {
            cliente = clienteRepository.findByCpf(cpf).get();

            if (cliente.getNome().equals(nome)) {
                return ResponseEntity.ok().build();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/recuperar-senha")
    public ResponseEntity<Void> recuperarSenha(@RequestBody ReculperarSenhaDTO infosUsr) {
        Cliente cliente;
        String cpf = infosUsr.getCpf();
        String nome = infosUsr.getNome();
        String email = infosUsr.getEmail();

        try {
            cliente = clienteRepository.findByCpf(cpf).get();

            if (cliente.getNome().equals(nome) && cliente.getEmail().equals(email)) {
                cliente.setSenha("123");
                cliente = clienteService.save(cliente);
                return ResponseEntity.ok().build();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return ResponseEntity.noContent().build();
    }


}
