package com.drip.banco.service.impl;

import com.drip.banco.dto.RealizarTransferenciaRequest;
import com.drip.banco.dto.TransferenciaResponse;
import com.drip.banco.entity.Conta;
import com.drip.banco.entity.ParametroTransferencia;
import com.drip.banco.entity.TipoTransaferencia;
import com.drip.banco.entity.Transferencia;
import com.drip.banco.exception.ContaException;
import com.drip.banco.exception.TipoTrasnferenciaException;
import com.drip.banco.repository.TransferenciaRepository;
import com.drip.banco.service.ContaService;
import com.drip.banco.service.ParametroTransferenciaService;
import com.drip.banco.service.TipoTransaferenciaService;
import com.drip.banco.service.TransferenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class TransferenciaServiceImpl implements TransferenciaService {

    private final TransferenciaRepository repository;
    private final ContaService contaService;
    private final TipoTransaferenciaService tipoTransaferenciaService;
    private final ParametroTransferenciaService parametroTransferenciaService;

    public List<TransferenciaResponse> findByTransferenciaContaOrigem(Long idContaOrigem) {
        List<Transferencia> listaTransferencia = repository.findByContaOrigemId(idContaOrigem);
        List<TransferenciaResponse> listaRetorno = new ArrayList<>();
        listaTransferencia.stream().forEach( transferencia ->
            listaRetorno.add(new TransferenciaResponse(transferencia.getContaOrigem().getCliente().getNome(),
                    transferencia.getContaDestino().getCliente().getNome(),
                    transferencia.getContaOrigem().getBanco().getNome(),
                    transferencia.getContaDestino().getBanco().getNome(),
                    transferencia.getTipo().getTipo(),
                    transferencia.getComissaoAplicada(),
                    transferencia.getValor(),
                    transferencia.getDataCriacao()))
        );
        return listaRetorno;
    }

    @Transactional(rollbackFor = Exception.class)
    public String realizarTrasferencia(Long idContaOrigem, Long idContaDestino,
                                       RealizarTransferenciaRequest transferenciaRequest) throws ContaException , TipoTrasnferenciaException {
        Transferencia transferencia = validarTransferencia(idContaOrigem,idContaDestino,transferenciaRequest);
        contaService.updateConta(transferencia.getContaOrigem());
        repository.save(transferencia);
        return "Transferencia Realizada";
    }

    private Transferencia validarTransferencia(Long idContaOrigem, Long idContaDestino,
                                 RealizarTransferenciaRequest transferenciaRequest){
        Conta contaOrigem = contaService.findById(idContaOrigem).orElseThrow(
                () -> new ContaException("Conta Origem nao encontrada = " + idContaOrigem));
        Conta contaDestino = contaService.findById(idContaDestino).orElseThrow(
                () -> new ContaException("Conta Destino nao encontrada = " + idContaDestino));
        if(contaOrigem.getId().equals(contaDestino.getId())){
            throw new ContaException("Conta Origem e Destino Iguais");
        }
        TipoTransaferencia tipo = tipoTransaferenciaService
                .findByTipo(transferenciaRequest.getTipoTransferencia()).orElseThrow(
                        () -> new ContaException("Erro ao consultar tipo de transferencia = "
                                + transferenciaRequest.getTipoTransferencia()));
        ParametroTransferencia parametro = parametroTransferenciaService.
                findByBancoIdAndTipoId(contaOrigem.getBanco().getId(), tipo.getId()).orElseThrow(
                        () -> new ContaException("Parametro de transferencia para o banco e tipo não encontrado = "));
        validarLimites(contaOrigem, contaDestino,transferenciaRequest.getValor(), parametro);
        return montarTransferencia(contaOrigem,contaDestino,tipo,parametro,transferenciaRequest.getValor());
    }

    void validarLimites(Conta contaOrigem, Conta contaDestino, double valor, ParametroTransferencia parametro){
        if(contaOrigem.getSaldo() <= (valor+parametro.getComissao())){
            throw new ContaException("Conta Origem nao possue saldo para a transferencia");
        }
        if(!contaOrigem.getBanco().getId().equals(contaDestino.getBanco().getId())){
            LocalDateTime dataInicio = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
            LocalDateTime dataFim = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
            List<Transferencia> listaBancosDiferentes = repository.findByTransferenciasParaContasExternas(contaOrigem.getId(),dataInicio,dataFim);
            Double somaTransferencia =  listaBancosDiferentes.stream()
                    .mapToDouble(Transferencia::getValor)
                    .sum();
           if((somaTransferencia+valor) > parametro.getLimiteDiario()){
               throw new ContaException("Conta Origem nao possue limites para a transferencia");
           }
        }
        contaOrigem.setSaldo(contaOrigem.getSaldo()-(valor+parametro.getComissao()));
    }

   public Transferencia montarTransferencia(Conta contaOrigem,
                                            Conta contaDestino, TipoTransaferencia tipo,
                                            ParametroTransferencia parametro, double valor){
        Transferencia transferencia = new Transferencia();
        transferencia.setContaOrigem(contaOrigem);
        transferencia.setContaDestino(contaDestino);
        transferencia.setTipo(tipo);
        transferencia.setComissaoAplicada(parametro.getComissao());
        transferencia.setValor(valor);
        transferencia.setDataCriacao(LocalDateTime.now());
        return transferencia;
    }




}
