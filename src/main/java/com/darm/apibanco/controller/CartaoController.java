package com.darm.apibanco.controller;

import com.darm.apibanco.DTO.CartaoDTO;
import com.darm.apibanco.model.Cartao;
import com.darm.apibanco.model.Cliente;
import com.darm.apibanco.model.TipoDeCartao;
import com.darm.apibanco.service.CartaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v1")
public class CartaoController {
    CartaoService cartaoService;

    @PostMapping("/criar-cartao")
    public ResponseEntity<Cartao> criarCartao(@RequestBody CartaoDTO cartaoDTO) {
        Cartao cartao = new Cartao();
        System.out.println(cartaoDTO.toString());

        switch (cartaoDTO.getTipoDeCartao()) {
            case "Debito" -> cartao.setTipoDeCartao(TipoDeCartao.DEBITO);
            case "Credito" -> cartao.setTipoDeCartao(TipoDeCartao.CREDITO);
            case "Pupanca" -> cartao.setTipoDeCartao(TipoDeCartao.POUPANCA);
            case "Credito-Debito" -> cartao.setTipoDeCartao(TipoDeCartao.CREDITO_DEBITO);
            case "Poupanca-Debito" -> cartao.setTipoDeCartao(TipoDeCartao.POUPANCA_DEBITO);
        }

        cartao.setCvc(cartaoDTO.getCvc());
        cartao.setNumero(cartaoDTO.getNumero());
        cartao.setDataDeValidade(cartao.data(cartaoDTO.getAnosDeValidade()));
        cartao.setBandeira(cartaoDTO.getBandeira());
        Cliente cliente = cartaoService.validacaoDeCliente(cartaoDTO.getNomeCliente());
        cartao.setCliente(cliente);
        System.out.println(cartao.toString());


        cartaoService.save(cartao);
        return ResponseEntity.ok(cartao);
    }

}
