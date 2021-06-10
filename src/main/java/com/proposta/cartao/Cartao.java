package com.proposta.cartao;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Cartao {


    @Id
    private String id;
    private  LocalDateTime emitidoEm;
    private  String titular;
    private  BigDecimal valorLimite;

    @ManyToOne(cascade = CascadeType.MERGE)
    private  Vencimento vencimento;




    @Deprecated
    public Cartao(){}

    public Cartao(String id, LocalDateTime emitidoEm, String titular, BigDecimal valorLimite, Vencimento vencimento) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.valorLimite = valorLimite;
        this.vencimento = vencimento;

    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getValorLimite() {
        return valorLimite;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }
}
