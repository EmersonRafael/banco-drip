package com.drip.banco.service;

import com.drip.banco.entity.ParametroTransferencia;

import java.util.Optional;

public interface ParametroTransferenciaService {

    Optional<ParametroTransferencia> findByBancoIdAndTipoId(Long idBanco, Long idTipo);

}
