package com.drip.banco.service;

import com.drip.banco.entity.Conta;

import java.util.Optional;

public interface ContaService {
   Optional<Conta> findById(Long idConta);

   void updateConta(Conta conta);
}
