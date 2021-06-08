package com.proposta.apiexternaanaliseproposta;

public class AnalisePropostaResponse {

    private String documento;
    private String nome;
    private String resultadoSolicitacao;
    private Long idProposta;


    @Deprecated
    public AnalisePropostaResponse(){}

    public AnalisePropostaResponse(String documento, String nome, String resultadoSolicitacao, Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
