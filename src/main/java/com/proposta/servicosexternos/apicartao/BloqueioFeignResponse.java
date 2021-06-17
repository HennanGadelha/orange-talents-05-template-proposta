package com.proposta.servicosexternos.apicartao;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

public class BloqueioFeignResponse {

    @JsonProperty
    private String resultado;

    @JsonCreator(mode = Mode.PROPERTIES)
    public BloqueioFeignResponse(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
