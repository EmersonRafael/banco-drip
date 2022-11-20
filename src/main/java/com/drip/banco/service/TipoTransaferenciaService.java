package com.drip.banco.service;

import com.drip.banco.entity.TipoTransaferencia;

import java.util.Optional;

public interface TipoTransaferenciaService {

    Optional<TipoTransaferencia> findByTipo(String tipo);

}
