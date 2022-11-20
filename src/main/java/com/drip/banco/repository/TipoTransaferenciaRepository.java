package com.drip.banco.repository;

import com.drip.banco.entity.TipoTransaferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoTransaferenciaRepository extends JpaRepository<TipoTransaferencia,Long> {

    Optional<TipoTransaferencia> findByTipoEquals(String tipo);

}
