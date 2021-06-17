package com.proposta.cartao.avisoviagem;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proposta.cartao.Cartao;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;


public class AvisoViagemRequest {

    private String destino;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @FutureOrPresent
    private LocalDate terminoPrevisto;


    public AvisoViagemRequest(String destino,     @JsonFormat(pattern = "yyyy/MM/dd", shape = JsonFormat.Shape.STRING)
            LocalDate terminoPrevisto, String ipAdress, String userAgent) {
        this.destino = destino;
        this.terminoPrevisto = terminoPrevisto;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getTerminoPrevisto() {
        return terminoPrevisto;
    }

    public AvisoViagem toModel(String ipAdress, String userAgent, Cartao cartao){
        return new AvisoViagem(this.destino,this.terminoPrevisto, ipAdress, userAgent, cartao);
    }

}
