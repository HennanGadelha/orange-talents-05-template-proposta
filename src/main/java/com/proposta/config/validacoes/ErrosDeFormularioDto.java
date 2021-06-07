package com.proposta.config.validacoes;

public class ErrosDeFormularioDto {

    private String campo;
    private String mensagemDeErro;

    public ErrosDeFormularioDto(String campo, String mensagemDeErro) {
        this.campo = campo;
        this.mensagemDeErro = mensagemDeErro;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagemDeErro() {
        return mensagemDeErro;
    }
}
