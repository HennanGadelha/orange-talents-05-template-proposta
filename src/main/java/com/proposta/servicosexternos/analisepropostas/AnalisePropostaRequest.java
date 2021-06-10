package com.proposta.servicosexternos.analisepropostas;

import com.proposta.proposta.Proposta;

public class AnalisePropostaRequest {

    private String nome;
    private String documento;
    private Long idProposta;


    public AnalisePropostaRequest(Proposta proposta) {
        this.nome = proposta.getNome();
        this.documento = proposta.getDocumento();
        this.idProposta = proposta.getId();
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
