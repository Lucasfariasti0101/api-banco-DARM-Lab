package com.darm.apibanco.service;

import com.darm.apibanco.DTO.AdicionarFotoDTO;
import com.darm.apibanco.DTO.VerificarCartaoDTO;
import com.darm.apibanco.DTO.DeletarClienteDTO;
import com.darm.apibanco.DTO.MudarSenhaDTO;
import com.darm.apibanco.exeption.DomainException;
import com.darm.apibanco.model.Cartao;
import com.darm.apibanco.model.Cliente;
import com.darm.apibanco.repository.CartaoRepository;
import com.darm.apibanco.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ClienteService {
    ClienteRepository clienteRepository;


    CartaoRepository cartaoRepository;

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

    @Transactional
    public boolean deletarCliente(DeletarClienteDTO dto) {

        if (verificarSenhaCpfCliente(dto.getSenha(), dto.getCpf())) {

            clienteRepository.deleteByCpfAndEmailAndSenha(dto.getCpf(),
                            dto.getSenha(),
                            dto.getEmail());
            return true;
        }

        return false;

    }

    @Transactional
    public Cliente upFotoCliente(AdicionarFotoDTO dto) {

        if (verificarSenhaCpfCliente(dto.getSenha(), dto.getCpf())) {
            Optional<Cliente> cliente = clienteRepository.findByCpf(dto.getCpf());
            Cliente clienteS = cliente.get();
            clienteRepository.save(clienteS);

            return clienteS;
        }
        throw new DomainException("Foto não cadastrada!");
    }

    @Transactional
    public Cliente mudarSenha(MudarSenhaDTO dto) {
        if (verificarSenhaCpfCliente(dto.getSenha(), dto.getCpf())) {
            Optional<Cliente> cliente = clienteRepository.findByCpf(dto.getCpf());
            Cliente clienteS = cliente.get();
            clienteRepository.saveAndFlush(clienteS);
            return clienteS;
        }
        throw new DomainException("A senha informada é invalida!");
    }

    public boolean verificarCpfCliente(String cpf) {
        boolean cpfInvalido = clienteRepository.findByCpf(cpf).isEmpty();

        if (cpfInvalido) {
            throw new DomainException("O cpf informado é invalido ou não esta cadastrado!");
        }
        return true;
    }

    public boolean verificarSenhaCpfCliente(String senha, String cpf) {
        boolean senhaInvalida = clienteRepository.findSenhaAndCpf(senha, cpf);

        if (!senhaInvalida) {
            throw new DomainException("A senha informada é invalida!");
        }
        return true;
    }

    @Transactional
    public boolean deletarCartao(VerificarCartaoDTO objDTO) {

        Cartao cartao = cartaoRepository.findByNum(objDTO.getNum()).get();

        if (Objects.equals(cartao.getNumero(), objDTO.getNum()) &&
                Objects.equals(cartao.getCvc(), objDTO.getCvc())) {

            clienteRepository.deletarCartaoByNumCvc(objDTO.getNum(), objDTO.getCvc());
            return true;
        }
        return false;
    }
}
