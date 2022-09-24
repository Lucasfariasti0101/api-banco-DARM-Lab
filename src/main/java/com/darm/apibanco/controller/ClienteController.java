package com.darm.apibanco.controller;

import com.darm.apibanco.DTO.*;
import com.darm.apibanco.model.Cliente;
import com.darm.apibanco.model.TipoDeConta;
import com.darm.apibanco.repository.ClienteRepository;
import com.darm.apibanco.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
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
        String senha= usuario.getSenha();

        try {
            cliente = clienteRepository.findByCpf(cpf).get();

            if (cliente.getSenha().equals(senha)) {
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
                clienteService.save(cliente);
                return ResponseEntity.ok().build();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/editar-perfil/adicionar-foto")
    public ResponseEntity<Cliente> adicionarFoto(@RequestBody AdicionarFotoDTO objDTO) {
        clienteService.upFotoCliente(objDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/editar-perfil/mudar-senha")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cliente> mudarSenha(@RequestBody MudarSenhaDTO objDTO) {
        clienteService.mudarSenha(objDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/editar-perfil/deletar")
    public ResponseEntity<Void> deletarPerfil(@RequestBody DeletarClienteDTO objDTO) {

       if (clienteService.deletarCliente(objDTO)) {
        return ResponseEntity.ok().build();
       }
        return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/cartao/deletar")
    public ResponseEntity<Void> deletarCartao(@RequestBody VerificarCartaoDTO objDTO) {

        if (clienteService.deletarCartao(objDTO)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
