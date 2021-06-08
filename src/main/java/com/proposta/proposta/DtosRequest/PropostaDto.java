package com.proposta.proposta.DtosRequest;

import com.proposta.config.validacoes.CpfOuCnpj;
import com.proposta.proposta.Proposta;
import com.sun.istack.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaDto {

    @NotNull @NotEmpty @CpfOuCnpj
    private String documento;
    @Email @NotEmpty @NotNull
    private String email;
    @NotEmpty @NotNull
    private String nome;
    @NotEmpty @NotNull
    private String endereco;
    @NotNull @Positive
    private BigDecimal salario;

    public PropostaDto(String email, String nome, String endereco, BigDecimal salario) {
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Proposta toModel() {
        return new Proposta(this.documento, this.email, this.nome, this.endereco, this.salario);
    }
}
