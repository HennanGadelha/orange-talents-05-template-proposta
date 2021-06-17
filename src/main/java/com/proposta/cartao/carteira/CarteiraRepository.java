package com.proposta.cartao.carteira;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
    Optional<Carteira> findByTipoCarteiraAndCartaoId(TipoCarteira tipoCarteira, String id);
}
