package com.proposta.proposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    Optional<Proposta> findByDocumento(String documento);

    @Query("SELECT p FROM Proposta p WHERE p.status = 'ELEGIVEL' and p.cartao is null ")
    List<Proposta> findByPropostasElegiveis();
}
