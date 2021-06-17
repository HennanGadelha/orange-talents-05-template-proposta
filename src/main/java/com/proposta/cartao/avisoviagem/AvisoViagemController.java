package com.proposta.cartao.avisoviagem;

import com.proposta.cartao.Cartao;
import com.proposta.cartao.CartaoRepository;
import com.proposta.config.validacoes.ExistingEntityException;
import com.proposta.servicosexternos.apicartao.ApiCartao;
import com.proposta.servicosexternos.apicartao.avisoviagem.AvisoViagemFeignRequest;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class AvisoViagemController {


    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    ApiCartao apiCartao;


    @PostMapping("/{id}/avisosviagens")
    public ResponseEntity<?> comunicarAvisoDeViagem(@PathVariable String id,
                                                    @RequestBody AvisoViagemRequest avisoViagemRequest,
                                                    UriComponentsBuilder uriComponentsBuilder,
                                                    HttpServletRequest http) throws ExistingEntityException {


        Optional<Cartao> existeCartao = cartaoRepository.findById(id);

        if(existeCartao.isEmpty()) throw  new ExistingEntityException(HttpStatus.NOT_FOUND, "Cartão não encontrado");

        Cartao cartao = existeCartao.get();
        String ipAddress = http.getRemoteAddr();
        String userAgent = http.getHeader("User-Agent");

        AvisoViagem avisoViagem = avisoViagemRequest.toModel(ipAddress, userAgent, cartao);
        URI uri = uriComponentsBuilder.path("/cartoes/{id}").buildAndExpand(cartao.getId()).toUri();
        AvisoViagemFeignRequest avisoViagemFeignRequest = new AvisoViagemFeignRequest(avisoViagem.getDestino(), avisoViagem.getTerminoPrevisto());

        try {

            cartao.comunicaViagem(avisoViagem);
            cartaoRepository.save(cartao);
            apiCartao.avisoViagem(cartao.getId(), avisoViagemFeignRequest);
            return  ResponseEntity.created(uri).build();

        } catch (FeignException exception){
            return ResponseEntity.unprocessableEntity().build();
        }

    }

}
