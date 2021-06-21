package com.proposta.proposta;

import com.proposta.cartao.Cartao;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String documento;
    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "numero_do_cartao")
    private Cartao cartao;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;


    public Proposta(){}

    public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario) {

        this.documento = CripografarDocumento.criptografar(documento);
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
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

    public Cartao getCartao() {
        return cartao;
    }

    public void adicionaCartao(Cartao cartao) {this.cartao = cartao;}

    public void resultadoStatusProposta(StatusEnum status){
        this.status = status;
    }

}
