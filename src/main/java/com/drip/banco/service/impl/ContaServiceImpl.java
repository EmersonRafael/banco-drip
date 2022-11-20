package com.drip.banco.service.impl;

import com.drip.banco.entity.Conta;
import com.drip.banco.repository.ContaRepository;
import com.drip.banco.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ContaServiceImpl implements ContaService {

    private final ContaRepository repository;

    public Optional<Conta> findById(Long idConta) {
        return repository.findById(idConta);
    }

    public void updateConta(Conta conta) {
        repository.save(conta);
    }

}
