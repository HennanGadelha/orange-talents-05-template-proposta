package com.proposta.cartao.biometria.dtos;

import com.proposta.cartao.Cartao;
import com.proposta.cartao.biometria.Biometria;
import com.proposta.config.validacoes.isbase64.IsBase64;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BiometriaRequest {

    @IsBase64 @NotEmpty @NotNull
    private String fingerprint;

    @Deprecated
    public BiometriaRequest() {}
    
    public BiometriaRequest(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public Biometria toModel(Cartao cartao){
        return new Biometria(this.fingerprint, cartao);
    }

    
}
