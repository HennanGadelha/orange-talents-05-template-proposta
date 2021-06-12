package com.proposta.servicosexternos.associacaocartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "AssociacaoCartao", url = "${api.associa.cartao}")
public interface AssociaCartao {

    @RequestMapping(method = RequestMethod.GET, value="${api.busca.cartao}?idProposta={idProposta}")
    AssociaCartaoResponse consultarCartaoDisponivel(@PathVariable("idProposta") String idProposta);

}
