package com.darm.apibanco.service;

import com.darm.apibanco.exeption.DomainException;
import com.darm.apibanco.model.Cartao;
import com.darm.apibanco.model.Cliente;
import com.darm.apibanco.repository.CartaoRepository;
import com.darm.apibanco.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartaoService {
    @Autowired
    CartaoRepository cartaoRepository;
    ClienteRepository clienteRepository;

    @Transactional
    public Cartao save(Cartao cartao) {
        boolean usedNum = cartaoRepository.findByNum(cartao.getNumero())
                .stream()
                .anyMatch(c -> !c.equals(cartao));
        if (usedNum) {
            throw new DomainException("O número do cartão já está sendo usado.");
        }
        return cartaoRepository.save(cartao);
    }

    public Cliente validacaoDeCliente(String nomeCliente) {
        boolean nomeInvalido = clienteRepository.findByNome(nomeCliente)
                .stream()
                .anyMatch(c -> c.equals(nomeCliente));
        if (nomeInvalido) {
            throw new DomainException("O nome informado é invalido ou não existe!");
        }
       return clienteRepository.findByNome(nomeCliente).get();
    }
}
