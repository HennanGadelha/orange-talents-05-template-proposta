package com.proposta.proposta.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proposta.servicosexternos.analisepropostas.AnaliseProposta;
import com.proposta.servicosexternos.analisepropostas.AnalisePropostaRequest;
import com.proposta.servicosexternos.analisepropostas.AnalisePropostaResponse;
import com.proposta.config.validacoes.ExistingProposalException;
import com.proposta.proposta.DtosRequest.PropostaDto;
import com.proposta.proposta.Proposta;
import com.proposta.proposta.PropostaRepository;
import com.proposta.proposta.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import feign.FeignException.UnprocessableEntity;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class CadastroProposta {

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    AnaliseProposta analisaProposta;

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity<?> cadastro(@RequestBody @Valid PropostaDto propostaRequest, UriComponentsBuilder uriBuilder) throws ExistingProposalException, JsonProcessingException /*throws ExistingProposalException*/ {

        verificaPropostaJaCadastrada(propostaRequest.getDocumento());

        Proposta proposta = propostaRequest.toModel();
        propostaRepository.save(proposta);

        analiseSePropostaValida(proposta);

        URI uri =  uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();

        return  ResponseEntity.created(uri).build();

    }

    public void analiseSePropostaValida(Proposta proposta) throws JsonProcessingException {
        try {
            analisaProposta.analisarPropostaValida( new AnalisePropostaRequest(proposta));
            proposta.resultadoStatusProposta(StatusEnum.ELEGIVEL);
        }catch(UnprocessableEntity ex) {

            String conteudoProposta = ex.contentUTF8();
            AnalisePropostaResponse propostaResponse = new ObjectMapper().readValue(conteudoProposta, AnalisePropostaResponse.class);

            if(propostaResponse.getResultadoSolicitacao().equals("COM_RESTRICAO"))
                proposta.resultadoStatusProposta(StatusEnum.NAO_ELEGIVEL);

        }
    }


    public void verificaPropostaJaCadastrada(String documento) throws ExistingProposalException {

        Optional<Proposta> propostaCadastrada = propostaRepository.findByDocumento(documento);
        if(propostaCadastrada.isPresent()) throw new ExistingProposalException(HttpStatus.UNPROCESSABLE_ENTITY,"Já existe uma proposta para esse documento");

    }


}
