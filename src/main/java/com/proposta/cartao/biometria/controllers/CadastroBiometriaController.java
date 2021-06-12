package com.proposta.cartao.biometria.controllers;

import com.proposta.cartao.Cartao;
import com.proposta.cartao.CartaoRepository;
import com.proposta.cartao.biometria.Biometria;
import com.proposta.cartao.biometria.BiometriaRepository;
import com.proposta.cartao.biometria.dtos.BiometriaRequest;
import com.proposta.config.validacoes.ExistingEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class CadastroBiometriaController {

    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    BiometriaRepository biometriaRepository;

    @PostMapping("/{id}/cadastrobiometria")
    public ResponseEntity<?> cadastroBiometria(@PathVariable String id, @RequestBody @Valid BiometriaRequest biometriaRequest,
                                               UriComponentsBuilder uriComponentsBuilder) throws ExistingEntityException {

        Optional<Cartao> cartao = cartaoRepository.findById(id);

        if(cartao.isEmpty()) throw  new ExistingEntityException(HttpStatus.NOT_FOUND, "Cartão não encontrado");

        Biometria biometria = biometriaRequest.toModel(cartao.get());
        biometriaRepository.save(biometria);

        URI uri = uriComponentsBuilder.path("/biometrias/{id}").buildAndExpand(biometria.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }


}
