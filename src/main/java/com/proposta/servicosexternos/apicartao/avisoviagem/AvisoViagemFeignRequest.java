package com.proposta.servicosexternos.apicartao.avisoviagem;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDate;

public class AvisoViagemFeignRequest {

    private String destino;
    private LocalDate validoAte;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AvisoViagemFeignRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
