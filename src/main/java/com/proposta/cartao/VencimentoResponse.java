package com.proposta.cartao;

import java.time.LocalDateTime;

public class VencimentoResponse {

    private String id;
    private int dia;
    private LocalDateTime dataDeCriacao;

    public VencimentoResponse(String id, int dia/*, LocalDateTime dataDeCriacao*/) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public int getDia() {
        return dia;
    }

    public LocalDateTime getDataCriacao() {
        return dataDeCriacao;
    }

    public Vencimento toModel(){
        return new Vencimento(this.id, this.dia, this.dataDeCriacao);
    }
}
