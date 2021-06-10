package com.proposta.proposta.controllers;

import com.proposta.proposta.Proposta;
import com.proposta.proposta.PropostaRepository;
import com.proposta.proposta.PropostaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class BuscarPropostaPorId {

    @Autowired
    private PropostaRepository propostaRepository;

    @GetMapping("/{id}")
    public ResponseEntity<PropostaResponse> buscarPorId(@PathVariable("id") Long id){

        Optional<Proposta> possivelProposta = propostaRepository.findById(id);

        PropostaResponse propostaResponse = new PropostaResponse(possivelProposta.get());
        return ResponseEntity.ok(propostaResponse);
    }


}
