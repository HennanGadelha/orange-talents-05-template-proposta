package com.proposta.proposta.controllers;

import com.proposta.config.validacoes.ExistingProposalException;
import com.proposta.proposta.DtosRequest.PropostaDto;
import com.proposta.proposta.Proposta;
import com.proposta.proposta.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class CadastroProposta {

    @Autowired
    PropostaRepository propostaRepositoryRepository;

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastro(@RequestBody @Valid PropostaDto propostaRequest, UriComponentsBuilder uriBuilder) throws ExistingProposalException /*throws ExistingProposalException*/ {

        verificaPropostaJaCadastrada(propostaRequest.getDocumento());

        Proposta proposta = propostaRequest.toModel();
        propostaRepositoryRepository.save(proposta);
        URI uri =  uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();

        return  ResponseEntity.created(uri).build();

    }

    public void verificaPropostaJaCadastrada(String documento) throws ExistingProposalException {

        Optional<Proposta> propostaCadastrada = propostaRepositoryRepository.findByDocumento(documento);
        if(propostaCadastrada.isPresent()) throw new ExistingProposalException("Já existe uma proposta para esse documento");

    }


}
