package com.drip.banco.controller;

import com.drip.banco.dto.RealizarTransferenciaRequest;
import com.drip.banco.dto.TransferenciaResponse;
import com.drip.banco.exception.ContaException;
import com.drip.banco.exception.TipoTrasnferenciaException;
import com.drip.banco.service.TransferenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("transferencia/origem/{idContaOrigem}")
public class TransferenciaController {

    private final TransferenciaService service;

    @GetMapping
    ResponseEntity<List<TransferenciaResponse>> findByTransferenciaContaOrigem(@NotNull @PathVariable("idContaOrigem") Long idContaOrigem){
        return ResponseEntity.status(HttpStatus.OK).body(service.findByTransferenciaContaOrigem(idContaOrigem));
    }

    @PostMapping("destino/{idContaDestino}")
    ResponseEntity<String> realizarTrasferencia(@NotNull @PathVariable("idContaOrigem") Long idContaOrigem,
                                                @NotNull @PathVariable("idContaDestino") Long idContaDestino,
                                                @RequestBody RealizarTransferenciaRequest transferenciaRequest){
        try{
            return  ResponseEntity.status(HttpStatus.OK)
                    .body(service.realizarTrasferencia(idContaOrigem,idContaDestino,transferenciaRequest));
        }catch (ContaException|TipoTrasnferenciaException e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
