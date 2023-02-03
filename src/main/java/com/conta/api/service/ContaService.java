package com.conta.api.service;

import com.conta.api.entity.Conta;
import com.conta.api.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Optional<Conta> getIdLimiteConta(String agencia, String numero, String digito){
        return contaRepository.getIdLimiteConta(agencia,numero,digito);
    }
}
