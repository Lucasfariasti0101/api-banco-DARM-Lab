package com.darm.apibanco.service;

import com.darm.apibanco.exeption.DomainException;
import com.darm.apibanco.model.Cartao;
import com.darm.apibanco.model.Cliente;
import com.darm.apibanco.repository.CartaoRepository;
import com.darm.apibanco.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CartaoService {
    @Autowired
    CartaoRepository cartaoRepository;
    @Autowired
    ClienteRepository clienteRepository;

    @Transactional
    public void save(Cartao cartao) {
        boolean usedNum = cartaoRepository.findByNum(cartao.getNumero()).isEmpty();
        if (!usedNum) {
            throw new DomainException("O cartão já está sendo usado.");
        }
        cartao.toString();
        cartaoRepository.save(cartao);
    }
    @Transactional
    public Cliente validacaoDeCliente(String nomeCliente) {
        boolean nomeInvalido = clienteRepository.findByNome(nomeCliente)
                .stream()
                .anyMatch(c -> c.equals(nomeCliente));
        if (nomeInvalido) {
            throw new DomainException("O nome informado é invalido ou cliente não esta cadastrado!");
        }

        Optional<Cliente> cliente;
        cliente = clienteRepository.findByNome(nomeCliente);
        return cliente.get();
    }
}
