package com.kalil.api_pagamentos.v1.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kalil.api_pagamentos.domain.model.MetodoPagamento;
import com.kalil.api_pagamentos.domain.service.MetodoPagamentoService;

import io.micrometer.common.lang.NonNull;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class MetodoPagamentoController {
    
    private final MetodoPagamentoService metodoPagamentoService;

    @GetMapping(value = "/metodosPagamento")
    @Transactional
    public ResponseEntity<List<MetodoPagamento>> listar() {
        List<MetodoPagamento> metodosPagamento = metodoPagamentoService.listarTodos();
        return new ResponseEntity<>(metodosPagamento, HttpStatus.OK);
    }
    


}
