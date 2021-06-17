package com.proposta.servicosexternos.apicartao;

import com.proposta.cartao.Cartao;
import com.proposta.cartao.vencimento.dto.VencimentoResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AssociaCartaoResponse {


    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private BigDecimal limite;
    private VencimentoResponse vencimento;
    private String idProposta;

    public AssociaCartaoResponse(String id, LocalDateTime emitidoEm, String titular, BigDecimal limite,
                                 VencimentoResponse vencimento, String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
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

    public BigDecimal getLimite() {
        return limite;
    }

    public VencimentoResponse getVencimento() {
        return vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public Cartao toModel(){

        return new Cartao(this.id, this.emitidoEm, this.titular, this.limite, vencimento.toModel());
    }


}
