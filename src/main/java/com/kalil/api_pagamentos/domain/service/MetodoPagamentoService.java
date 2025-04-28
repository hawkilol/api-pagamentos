package com.kalil.api_pagamentos.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kalil.api_pagamentos.domain.model.MetodoPagamento;
import com.kalil.api_pagamentos.domain.repository.MetodoPagamentoRepository;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor  
public class MetodoPagamentoService {
    
    @NonNull
    private final MetodoPagamentoRepository metodoPagamentoRepository;

    public MetodoPagamento read(@NotNull Long id){
        return metodoPagamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Metodo de pagamento com id " + id + " não encontrado"));
    }

    public List<MetodoPagamento> listarTodos(){
        return metodoPagamentoRepository.findAll();
    }
}
