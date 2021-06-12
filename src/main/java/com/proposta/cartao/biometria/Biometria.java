package com.proposta.cartao.biometria;

import com.proposta.cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Biometria {

	@Deprecated
	public Biometria() {}
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fingerprint;
    private LocalDateTime criadoEm;

    @ManyToOne
    private Cartao cartao;

    public Biometria(String fingerprint,  Cartao cartao) {
        this.fingerprint = fingerprint;
        this.criadoEm = LocalDateTime.now();
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }
}
