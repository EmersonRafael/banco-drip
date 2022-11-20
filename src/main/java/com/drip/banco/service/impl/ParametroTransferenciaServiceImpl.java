package com.drip.banco.service.impl;

import com.drip.banco.entity.ParametroTransferencia;
import com.drip.banco.repository.ParametroTransferenciaRepository;
import com.drip.banco.service.ParametroTransferenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ParametroTransferenciaServiceImpl implements ParametroTransferenciaService {

    private final ParametroTransferenciaRepository repository;

    public Optional<ParametroTransferencia> findByBancoIdAndTipoId(Long idBanco, Long idTipo){
        return repository.findByBancoIdAndTipoId(idBanco, idTipo);
    }
}
