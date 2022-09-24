package com.darm.apibanco.controller;

import com.darm.apibanco.DTO.LoginDTO;
import com.darm.apibanco.DTO.ReculperarSenhaDTO;
import com.darm.apibanco.DTO.ValidarCartaoDTO;
import com.darm.apibanco.exeption.DomainException;
import com.darm.apibanco.model.Administrador;
import com.darm.apibanco.model.Cartao;
import com.darm.apibanco.model.Cliente;
import com.darm.apibanco.service.AdmService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/adm")
public class AdminController {

    AdmService admService;


    @PostMapping("/cadastrar-adm")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Administrador> cadastrarAdm(@Valid @RequestBody Administrador adm) {

        Administrador administrador = admService.cadastrar(adm);
        return ResponseEntity.ok(administrador);

    }

    @PostMapping("/login")
    public ResponseEntity<Administrador> login(@RequestBody LoginDTO dto) {
        String cpf;
        String senha;
        if (admService.login(dto.getCpf(), dto.getSenha()).isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/recuperar-senha")
    @ResponseStatus(HttpStatus.CREATED)
    public String recuperarSenha(@RequestBody ReculperarSenhaDTO dto) {
        return admService.recuperarSenha(dto.getEmail(), dto.getNome(), dto.getCpf());
    }

    @PostMapping("/aprovar-cartao")
    @ResponseStatus(HttpStatus.CREATED)
    public Cartao aprovarCartao(@RequestBody ValidarCartaoDTO dto) {

        return admService.aprovarCartao(dto.getNum(), dto.getCvc(), dto.getStatus());

    }

    @GetMapping("/listar-porEstado")
    public ResponseEntity<List<Cliente>> listarClientes(@RequestParam(value = "estado") String estado) {
        if (!admService.listarClientesEstado(estado).isEmpty()) {
            List<Cliente> clientes = admService.listarClientesEstado(estado);
            return ResponseEntity.ok(clientes);
        }
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/listar-qtdCartoes")
    public List<Cliente> listarClientes(@RequestParam(value = "numero_de_cartoes") int numCartoes) {

        List<Cliente> clientes = admService.listarClientesQtdCartoes(numCartoes);
        return clientes;
    }

    @GetMapping("/listar-ordem-alfabetica")
    public List<Cliente> listarClientesOrdemAlfabetica() {
        return admService.listarClientesOrdemAlfabetica();


    }

}
