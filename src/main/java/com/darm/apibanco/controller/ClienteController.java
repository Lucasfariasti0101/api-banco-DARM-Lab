package com.darm.apibanco.controller;

import com.darm.apibanco.model.Cliente;
import com.darm.apibanco.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvarCliente(@Valid @RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }


}
