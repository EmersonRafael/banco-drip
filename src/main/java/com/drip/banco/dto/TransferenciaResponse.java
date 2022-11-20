package com.drip.banco.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TransferenciaResponse {
    String clienteOrigem;
    String clienteDestino;
    String bancoOrigem;
    String bancoDestino;
    String tipoTrasferencia;
    double comissaoAplicada;
    double valor;
    LocalDateTime dataCriacao;
}
