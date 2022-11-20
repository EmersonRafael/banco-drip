package com.drip.banco.service.impl;

import com.drip.banco.repository.ClienteRepository;
import com.drip.banco.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

}
