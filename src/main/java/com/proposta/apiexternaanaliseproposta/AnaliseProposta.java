package com.proposta.apiexternaanaliseproposta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "analisePinanceiro", url = "${zup.analise_financeira.url}")
public interface AnaliseProposta {

    @RequestMapping(method = RequestMethod.POST, value="${apiexternaanaliseproposta.request}")
    AnalisePropostaResponse analisarPropostaValida(AnalisePropostaRequest proposta);

}
