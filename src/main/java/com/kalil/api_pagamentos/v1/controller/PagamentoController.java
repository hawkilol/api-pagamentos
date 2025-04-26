package com.kalil.api_pagamentos.v1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kalil.api_pagamentos.domain.service.PagamentoService;

import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    // @PostMapping(value = "/pagamentos", params = {"size", "page"})
    // @Transactional
    // public ResponseEntity<Page<PagamentoOut>> listar(Pageable pageable, @RequestBody PagamentoIn pagamentoIn) {
        
    //     Page<Pagamento> pagamentos = pagamentoService.pesquisar(pageable);
    //     Page<PagamentoOut> retorno = 
    //     return ResponseEntity.ok().body(retorno);
    // }

    // @PostMapping(value = "/pagamentos", params = {"size", "page"})
    // @Transactional
    // public ResponseEntity<Page<PagamentoOut>> listarPorFiltro(Pageable pageable, @RequestBody PagamentoIn pagamentoIn) {
        
    //     Page<Pagamento> pagamentos = pagamentoService.pesquisar(pageable);
    //     Page<PagamentoOut> retorno = 
    //     return new ResponseEntity<>(retorno, HttpStatus.OK);
    // }




}
