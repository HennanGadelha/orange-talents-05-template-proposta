package com.proposta.servicosexternos.apicartao;

import com.proposta.cartao.Cartao;
import com.proposta.proposta.Proposta;
import com.proposta.proposta.PropostaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscaAgendadaCartao {

    @Autowired
    private ApiCartao apiCartao;

    @Autowired
    private PropostaRepository propostaRepository;


    @Scheduled(fixedDelay = 120000)
    public void associaCartao(){

        List<Proposta> propostasAprovadas = propostaRepository.findByPropostasElegiveis();

        try{

            for (Proposta proposta : propostasAprovadas){
                AssociaCartaoResponse cartaoResponse = apiCartao.consultarCartaoDisponivel(proposta.getId().toString());
                Cartao cartao = cartaoResponse.toModel();
                proposta.adicionaCartao(cartao);
                propostaRepository.save(proposta);
            }

        }catch (FeignException ex){

            ex.printStackTrace();
        }



    }

}
