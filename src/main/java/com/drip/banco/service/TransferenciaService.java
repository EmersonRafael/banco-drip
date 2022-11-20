package com.drip.banco.service;

import com.drip.banco.dto.RealizarTransferenciaRequest;
import com.drip.banco.dto.TransferenciaResponse;
import com.drip.banco.exception.ContaException;
import com.drip.banco.exception.TipoTrasnferenciaException;

import java.util.List;

public interface TransferenciaService {
    List<TransferenciaResponse> findByTransferenciaContaOrigem(Long idContaOrigem);
    String realizarTrasferencia(Long idContaOrigem, Long idContaDestino, RealizarTransferenciaRequest transferenciaRequest) throws ContaException, TipoTrasnferenciaException;

}
