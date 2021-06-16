package com.proposta.cartao.bloqueio;

import com.proposta.cartao.Cartao;
import com.proposta.cartao.CartaoRepository;
import com.proposta.config.validacoes.ExistingEntityException;
import com.proposta.servicosexternos.associacaocartao.ApiCartao;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class BloqueioCartaoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ApiCartao apiCartao;

    @PostMapping("/{id}/bloqueios")
    public ResponseEntity<?> bloqueiaCartao(@PathVariable String id, HttpServletRequest http) throws ExistingEntityException {

        Optional<Cartao> existeCartao = cartaoRepository.findById(id);

        System.out.println(existeCartao.get().getTitular());

        if(existeCartao.isEmpty()) throw  new ExistingEntityException(HttpStatus.NOT_FOUND, "Cartão não encontrado");

        Cartao cartao = existeCartao.get();

        if(cartao.ExisteBloqueioAtivo()) return  ResponseEntity.unprocessableEntity().build();

        String ipAdress = http.getRemoteAddr();
        String userAgent = http.getHeader("User-Agent");

        BloqueioFeignRequest bloqueioFeignRequest = new BloqueioFeignRequest("api-proposta-Hennan Gadelha");

        try {
            apiCartao.bloqueioCartao(cartao.getId(), bloqueioFeignRequest);
            cartao.bloqueia(ipAdress, userAgent);
            cartaoRepository.save(cartao);
            return ResponseEntity.ok().build();

        }catch (FeignException ex){
            return ResponseEntity.unprocessableEntity().build();
        }

    }

}
