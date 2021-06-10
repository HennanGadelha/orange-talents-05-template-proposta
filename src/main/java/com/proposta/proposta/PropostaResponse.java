package com.proposta.proposta;

import com.proposta.cartao.CartaoResponse;

import java.math.BigDecimal;

public class PropostaResponse {

    private Long idProposta;
    private String documento;
    private String nome;
    private String email;
    private String endereco;
    private BigDecimal salario;
    private StatusEnum status;

    private CartaoResponse cartao;

    public PropostaResponse(Proposta proposta) {
        this.idProposta = proposta.getId();
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.email = proposta.getEmail();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.status = proposta.getStatus();
        if (proposta.getCartao() != null) {
            this.cartao = new CartaoResponse(proposta.getCartao());
        }
    }

    public void setCartao(CartaoResponse cartao) {
        this.cartao = cartao;
    }

    public Long getId() {
        return idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public StatusEnum getStatus() {
        return status;
    }


}
