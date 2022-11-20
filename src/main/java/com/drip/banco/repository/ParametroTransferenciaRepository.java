package com.drip.banco.repository;

import com.drip.banco.entity.ParametroTransferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParametroTransferenciaRepository extends JpaRepository<ParametroTransferencia,Long> {
    Optional<ParametroTransferencia> findByBancoIdAndTipoId(Long idBanco, Long idTipo);
}
