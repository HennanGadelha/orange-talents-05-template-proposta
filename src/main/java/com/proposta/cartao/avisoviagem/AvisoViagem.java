package com.proposta.cartao.avisoviagem;

import com.proposta.cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AvisoViagem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destino;
    private LocalDateTime dataAviso;
    private LocalDate terminoPrevisto;
    private String ipAdress;
    private  String userAgent;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public AvisoViagem(){}

    public AvisoViagem(String destino,  LocalDate terminoPrevisto, String ipAdress, String userAgent,
                       Cartao cartao) {

        this.destino = destino;
        this.dataAviso = LocalDateTime.now();
        this.terminoPrevisto = terminoPrevisto;
        this.ipAdress = ipAdress;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getTerminoPrevisto() {
        return terminoPrevisto;
    }
}
