package com.proposta.servicosexternos.apicartao;

import com.proposta.cartao.bloqueio.BloqueioFeignRequest;
import com.proposta.servicosexternos.apicartao.avisoviagem.AvisoViagemFeignRequest;
import com.proposta.servicosexternos.apicartao.avisoviagem.AvisoViagemFeignResponse;
import com.proposta.servicosexternos.apicartao.carteira.CarteiraFeignRequest;
import com.proposta.servicosexternos.apicartao.carteira.CarteiraFeignResponse;
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

    @RequestMapping(method = RequestMethod.POST, path = "${api.busca.cartao}/{id}/carteiras")
    CarteiraFeignResponse associaCarteira(@PathVariable("id") String id, CarteiraFeignRequest carteiraFeignRequest);

}
