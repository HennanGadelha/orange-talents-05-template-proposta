package com.proposta.cartao.carteira;

import com.proposta.cartao.Cartao;
import com.proposta.config.validacoes.valueofenum.ValueOfEnum;

import javax.validation.constraints.Email;

public class CarteiraRequest {

    @Email
    private String email;

    @ValueOfEnum(enumClass = TipoCarteira.class)
    private String carteira;

    public CarteiraRequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }

    public Carteira toModel(Cartao cartao) {
        return  new Carteira(this.email, TipoCarteira.valueOf(this.carteira), cartao);
    }
}
