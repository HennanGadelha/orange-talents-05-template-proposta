package com.proposta.cartao.bloqueio;

import com.proposta.cartao.Cartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class BloqueioCartao {


    @Id
    @GeneratedValue
    private Long id;
    private String ipAdress;
    private String userAgent;
    private LocalDateTime emitidoEm;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public BloqueioCartao(){}

    public BloqueioCartao(String ipAdress, String userAgent, Cartao cartao) {
        this.ipAdress = ipAdress;
        this.userAgent = userAgent;
        this.emitidoEm = LocalDateTime.now();
        this.cartao = cartao;
    }
}
