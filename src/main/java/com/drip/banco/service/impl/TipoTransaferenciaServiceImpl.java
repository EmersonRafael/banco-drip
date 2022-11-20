package com.drip.banco.service.impl;

import com.drip.banco.entity.TipoTransaferencia;
import com.drip.banco.repository.TipoTransaferenciaRepository;
import com.drip.banco.service.TipoTransaferenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TipoTransaferenciaServiceImpl implements TipoTransaferenciaService {

    private final TipoTransaferenciaRepository repository;

    @Override
    public Optional<TipoTransaferencia> findByTipo(String tipo) {
        return repository.findByTipoEquals(tipo);
    }
}
