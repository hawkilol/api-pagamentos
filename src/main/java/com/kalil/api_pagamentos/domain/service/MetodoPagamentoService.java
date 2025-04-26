package com.kalil.api_pagamentos.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kalil.api_pagamentos.domain.model.MetodoPagamento;
import com.kalil.api_pagamentos.domain.model.Pagamento;
import com.kalil.api_pagamentos.domain.repository.MetodoPagamentoRepository;

import io.micrometer.common.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor  
public class MetodoPagamentoService {
    
    @NonNull
    private final MetodoPagamentoRepository metodoPagamentoRepository;

    public List<MetodoPagamento> listarTodos(){
        return metodoPagamentoRepository.findAll();
    }
}
