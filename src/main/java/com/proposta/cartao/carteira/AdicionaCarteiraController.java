package com.proposta.cartao.carteira;

import com.proposta.cartao.Cartao;
import com.proposta.cartao.CartaoRepository;
import com.proposta.config.validacoes.ExistingEntityException;
import com.proposta.servicosexternos.apicartao.ApiCartao;
import com.proposta.servicosexternos.apicartao.carteira.CarteiraFeignRequest;
import feign.FeignException;
import net.bytebuddy.asm.Advice;
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
public class AdicionaCarteiraController {


    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    CarteiraRepository carteiraRepository;

    @Autowired
    ApiCartao apiCartao;


    @PostMapping("/{id}/adicionarcarteira")
    public ResponseEntity<?> adicionarCarteura(@PathVariable String id,
                                               @RequestBody @Valid CarteiraRequest carteiraRequest,
                                               UriComponentsBuilder uriComponentsBuilder) throws ExistingEntityException {

        Optional<Cartao> existeCartao = cartaoRepository.findById(id);

        if(existeCartao.isEmpty()) throw  new ExistingEntityException(HttpStatus.NOT_FOUND, "Cartão não encontrado");

        Cartao cartao = existeCartao.get();

        Carteira carteira = carteiraRequest.toModel(cartao);

        Optional<Carteira> existeCarteira = carteiraRepository.findByTipoCarteiraAndCartaoId(carteira.getTipoCarteira(), carteira.getCartao().getId());

        if(existeCarteira.isPresent()) throw  new ExistingEntityException(HttpStatus.UNPROCESSABLE_ENTITY, "Carteira já cadastrada");

        URI uri = uriComponentsBuilder.path("/cartoes/{id}").buildAndExpand(cartao.getId()).toUri();

        try{
            carteiraRepository.save(carteira);
            apiCartao.associaCarteira(cartao.getId(), new CarteiraFeignRequest(carteira.getEmail(), carteira.getId().toString()));
            return ResponseEntity.created(uri).build();

        }catch(FeignException exception){
            return ResponseEntity.unprocessableEntity().build();
        }

    }

}
