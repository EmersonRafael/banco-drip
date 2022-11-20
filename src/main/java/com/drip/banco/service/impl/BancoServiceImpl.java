package com.drip.banco.service.impl;

import com.drip.banco.repository.BancoRepository;
import com.drip.banco.service.BancoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BancoServiceImpl implements BancoService {

    private final BancoRepository repository;


}
