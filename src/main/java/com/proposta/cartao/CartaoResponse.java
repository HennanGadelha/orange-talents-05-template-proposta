package com.proposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CartaoResponse {

    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private BigDecimal limite;
    private Vencimento vencimento;


    public CartaoResponse(){}


    public CartaoResponse(Cartao cartao) {
        this.id = cartao.getId();
        emitidoEm = cartao.getEmitidoEm();
        this.titular = cartao.getTitular();
        this.limite = cartao.getValorLimite();
        this.vencimento = cartao.getVencimento();

    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEM() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getlimite() {
        return limite;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }
}
