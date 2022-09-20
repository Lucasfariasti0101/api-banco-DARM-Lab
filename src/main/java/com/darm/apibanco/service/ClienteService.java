package com.darm.apibanco.service;

import com.darm.apibanco.exeption.DomainException;
import com.darm.apibanco.model.Cliente;
import com.darm.apibanco.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Transactional
    public Cliente save(Cliente cliente) {
        boolean usedCpf = clienteRepository.findByCpf(cliente.getCpf())
                .stream()
                .anyMatch(c -> !c.equals(cliente));
        if (usedCpf) {
            throw new DomainException("There is already a customer with that email!");
        }
        return clienteRepository.save(cliente);
    }
}
