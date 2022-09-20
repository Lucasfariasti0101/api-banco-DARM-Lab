package com.darm.apibanco.controller;

import com.darm.apibanco.DTO.CartaoDTO;
import com.darm.apibanco.model.Cartao;
import com.darm.apibanco.model.TipoDeCartao;
import com.darm.apibanco.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class CartaoController {

    @Autowired
    CartaoService cartaoService;

    @PostMapping("/criar-cartao")
    public ResponseEntity<Void> criarCartao(@RequestBody CartaoDTO cartaoDTO) {
        Cartao cartao = new Cartao();

        switch (cartaoDTO.getTipoDeCartao()) {
            case "Debito" -> cartao.setTipoDeCartao(TipoDeCartao.DEBITO);
            case "Credito" -> cartao.setTipoDeCartao(TipoDeCartao.CREDITO);
            case "Pupanca" -> cartao.setTipoDeCartao(TipoDeCartao.POUPANCA);
            case "Credito-Debito" -> cartao.setTipoDeCartao(TipoDeCartao.CREDITO_DEBITO);
            case "Poupanca-Debito" -> cartao.setTipoDeCartao(TipoDeCartao.POUPANCA_DEBITO);
        }

        cartao.setCvc(cartaoDTO.getCvc());
        cartao.setNumero(cartao.getNumero());
        cartao.setDataDeValidade(cartao.data(cartaoDTO.getAnosDeValidade()));
        cartao.setBandeira(cartaoDTO.getBandeira());

        cartao.setCliente(cartaoService.validacaoDeCliente(cartaoDTO.getNomeCliente()));

        cartaoService.save(cartao);
        return ResponseEntity.ok().build();
    }

}
