package com.proposta.cartao.carteira;

import com.proposta.cartao.Cartao;

import javax.persistence.*;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    @Enumerated(EnumType.STRING)
    private TipoCarteira tipoCarteira;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Carteira(){}

    public Carteira(String email, TipoCarteira tipoCarteira, Cartao cartao) {
        this.email = email;
        this.tipoCarteira = tipoCarteira;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getTipoCarteira() {
        return tipoCarteira;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
