package com.proposta.servicosexternos.associacaocartao;

import com.proposta.cartao.bloqueio.BloqueioFeignRequest;
import com.proposta.servicosexternos.associacaocartao.avisoviagem.AvisoViagemFeignRequest;
import com.proposta.servicosexternos.associacaocartao.avisoviagem.AvisoViagemFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "AssociacaoCartao", url = "${api.associa.cartao}")
public interface ApiCartao {

    @RequestMapping(method = RequestMethod.GET, value="${api.busca.cartao}?idProposta={idProposta}")
    AssociaCartaoResponse consultarCartaoDisponivel(@PathVariable("idProposta") String idProposta);

    @RequestMapping(method = RequestMethod.POST, path="${api.busca.cartao}/{id}/bloqueios")
    BloqueioFeignResponse bloqueioCartao(@PathVariable("id") String id, BloqueioFeignRequest bloqueioFeignRequest);

    @RequestMapping(method =  RequestMethod.POST, path = "${api.busca.cartao}/{id}/avisos")
    AvisoViagemFeignResponse avisoViagem(@PathVariable("id") String id, AvisoViagemFeignRequest avisoViagemRequest);

}
