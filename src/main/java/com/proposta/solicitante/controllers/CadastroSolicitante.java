package com.proposta.solicitante.controllers;

import com.proposta.solicitante.DtosRequest.SolicitanteDto;
import com.proposta.solicitante.Solicitante;
import com.proposta.solicitante.SolicitanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/solicitantes")
public class CadastroSolicitante {

    @Autowired
    SolicitanteRepository solicitanteRepository;

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastro(@RequestBody @Valid SolicitanteDto solicitanteRequest, UriComponentsBuilder uriBuilder){

        Solicitante solicitante = solicitanteRequest.toModel();
        solicitanteRepository.save(solicitante);

        URI uri =  uriBuilder.path("/solicitantes/{id}").buildAndExpand(solicitante.getId()).toUri();

        return  ResponseEntity.created(uri).build();

    }

}
