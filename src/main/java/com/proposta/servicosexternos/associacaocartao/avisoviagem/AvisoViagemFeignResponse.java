package com.proposta.servicosexternos.associacaocartao.avisoviagem;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;


public class AvisoViagemFeignResponse {


    @JsonProperty
    private String resultado;

    @JsonCreator(mode = Mode.PROPERTIES)
    public AvisoViagemFeignResponse(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
