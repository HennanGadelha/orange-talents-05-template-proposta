package com.proposta.servicosexternos.apicartao.carteira;

public class CarteiraFeignRequest {

    private String email;
    private String carteira;

    public CarteiraFeignRequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
