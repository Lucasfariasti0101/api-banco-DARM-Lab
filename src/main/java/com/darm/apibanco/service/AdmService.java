package com.darm.apibanco.service;

import com.darm.apibanco.DTO.LoginDTO;
import com.darm.apibanco.exeption.DomainException;
import com.darm.apibanco.model.Administrador;
import com.darm.apibanco.model.Cartao;
import com.darm.apibanco.model.Cliente;
import com.darm.apibanco.model.StatusCartao;
import com.darm.apibanco.repository.AdmRepository;
import com.darm.apibanco.repository.CartaoRepository;
import com.darm.apibanco.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AdmService {


    AdmRepository admRepository;
    CartaoRepository cartaoRepository;
    ClienteRepository clienteRepository;

    @Transactional
    public Administrador cadastrar(Administrador adm) {
        boolean usedCpf = admRepository.findByCpf(adm.getCpf());

        if (usedCpf == false) {
            admRepository.save(adm);

            return adm;
        }

        throw new DomainException("There is already a customer with that email!");
    }

    @Transactional
    public Optional<Administrador> login(String cpf, String senha) {
        boolean usedCpf = admRepository.findByCpf(cpf);

        if (usedCpf) {
            Administrador adm = admRepository.getByCpf(cpf);
            if (adm.getSenha().equals(senha)) {
                return Optional.of(adm);
            }
        }
        throw new DomainException("A senha e ou cpf não estão corretos.");
    }

    @Transactional
    public String recuperarSenha(String email, String nome, String cpf) {
        boolean usedCpf = admRepository.findByCpf(cpf);

        if (usedCpf) {
            Administrador adm = admRepository.getByCpf(cpf);
            if (adm.getEmail().equals(email) && adm.getNome().equals(nome)) {
                adm.setSenha("senhaNova");
                admRepository.saveAndFlush(adm);
                return adm.getSenha();
            }
        }
        throw new DomainException("Os dados não estão corretos.");
    }

    @Transactional
    public Cartao aprovarCartao(String num, String cvc, int status) {

        if (cartaoRepository.getByNumCvc(num, cvc).isPresent()) {
            Cartao cartao = cartaoRepository.findByNum(num).get();
            switch (status) {
                case 1 -> cartao.setStatusCartao(StatusCartao.APROVADO);
                case 2 -> cartao.setStatusCartao(StatusCartao.REPROVADO);
                default -> cartao.setStatusCartao(StatusCartao.PENDENTE);
            }
            cartaoRepository.save(cartao);
            return cartao;
        }
        throw new DomainException("O número do cartão e ou o CVC não estão cadastrados ou estão incorretos.");
    }

    @Transactional
    public List<Cliente> listarClientesEstado(String estado) {
        if (!admRepository.findAllByEstado(estado).isEmpty()) {
            return admRepository.findAllByEstado(estado);
        }
        throw new DomainException("Não ha clientes deste estado.");
    }

    @Transactional
    public List<Cliente> listarClientesQtdCartoes(int qtdCartoes) {
        List<Cartao> cartaos = cartaoRepository.findAll();
        List<Cliente> clientes = clienteRepository.findAll();
        List<Cliente> clienteList = new ArrayList<>();
        int aux = 0;

        for (Cliente cliente : clientes) {
            for (Cartao cartao : cartaos) {
                if (cartao.getCliente().getId().equals(cliente.getId())) {
                    aux++;
                    if (aux == qtdCartoes) {
                        clienteList.add(cliente);
                        aux = 0;
                    }
                }
            }
        }

        return clienteList;
    }

    @Transactional
    public List<Cliente> listarClientesOrdemAlfabetica() {
        if (!clienteRepository.findAll().isEmpty()) {
            List<Cliente> clientes = clienteRepository.findAllByOrdemAlfabetica();
            return clientes;
        }
        throw new DomainException("Nenhum cliente cadastrado.");
    }
}
