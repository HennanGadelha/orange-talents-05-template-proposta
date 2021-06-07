package com.proposta.solicitante.DtosRequest;

import com.proposta.config.validacoes.CpfOuCnpj;
import com.proposta.solicitante.Solicitante;
import com.sun.istack.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class SolicitanteDto {

    @NotNull @CpfOuCnpj
    private String documento;
    @Email @NotEmpty @NotNull
    private String email;
    @NotEmpty @NotNull
    private String nome;
    @NotEmpty @NotNull
    private String endereco;
    @NotNull @Positive
    private BigDecimal salario;

    public SolicitanteDto(String email, String nome, String endereco, BigDecimal salario) {
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

    public Solicitante toModel() {
        return new Solicitante(this.documento, this.email, this.nome, this.endereco, this.salario);
    }
}
