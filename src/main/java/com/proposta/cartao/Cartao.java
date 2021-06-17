package com.proposta.cartao;

import com.proposta.cartao.avisoviagem.AvisoViagem;
import com.proposta.cartao.biometria.Biometria;
import com.proposta.cartao.bloqueio.BloqueioCartao;
import com.proposta.cartao.vencimento.Vencimento;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cartao {


    @Id
    private String id;
    private  LocalDateTime emitidoEm;
    private  String titular;
    private  BigDecimal valorLimite;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;


    @ManyToOne(cascade = CascadeType.MERGE)
    private Vencimento vencimento;

    @OneToMany(mappedBy = "cartao")
    private List<Biometria> biometrias = new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
    private List<BloqueioCartao> bloqueios = new ArrayList<>();

    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private List<AvisoViagem> avisosDeViagens = new ArrayList<>();

    @Deprecated
    public Cartao(){}

    public Cartao(String id, LocalDateTime emitidoEm, String titular, BigDecimal valorLimite, Vencimento vencimento) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.valorLimite = valorLimite;
        this.vencimento = vencimento;
        this.situacao = Situacao.NAO_BLOQUEADO;

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

    public void bloqueia(String ipAdress, String userAgent){

        this.situacao = Situacao.BLOQUEADO;

        adicionaBloqueio(new BloqueioCartao(ipAdress, userAgent, this));
    }

    private void adicionaBloqueio(BloqueioCartao bloqueioCartao) {

        bloqueios.add(bloqueioCartao);
    }

    public boolean ExisteBloqueioAtivo(){
        return this.situacao.equals(Situacao.BLOQUEADO);
    }

    public void comunicaViagem(AvisoViagem avisoViagem){
        avisosDeViagens.add(avisoViagem);
    }

    public List<AvisoViagem> getAvisosDeViagens() {
        return avisosDeViagens;
    }
}
