package com.kalil.api_pagamentos.v1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.kalil.api_pagamentos.domain.model.Pagamento;
import com.kalil.api_pagamentos.domain.service.PagamentoService;
import com.kalil.api_pagamentos.v1.dto.PagamentoIn;
import com.kalil.api_pagamentos.v1.dto.PagamentoOut;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import com.kalil.api_pagamentos.v1.dto.FiltroPagamento;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    private ModelMapper modelMapper;

    @PostMapping(value = "/pagamento")
    @Transactional
    public ResponseEntity<Pagamento> criarPag(@RequestBody PagamentoIn pagamentoIn){
        System.out.println("DadosPagamento: ");
        System.out.println(pagamentoIn);
        Pagamento pagamento = pagamentoService.criar(pagamentoIn);
        return new ResponseEntity<>(pagamento, HttpStatus.CREATED);
    }
    
    @PostMapping(value = "/pagamentos-listar", params = {"size", "page"})
    @Transactional
    public ResponseEntity<Page<PagamentoOut>> listarPorFiltro(Pageable pageable, @RequestBody FiltroPagamento filtroPagamento){
        
        Page<Pagamento> pagamentos = pagamentoService.listarPorFiltro(filtroPagamento, pageable);
        Page<PagamentoOut> pagamentosOut = pagamentos.map(fo -> {
            return modelMapper.map(fo, PagamentoOut.class);
        });
        return new ResponseEntity<>(pagamentosOut, HttpStatus.OK);
    }

    @PutMapping(value = "/pagamento-status/{pagamentoId}")
    @Transactional
    public ResponseEntity<Pagamento> atualizarStatus(@PathVariable Long pagamentoId, @RequestBody Pagamento.StatusPagamento statusNovo){
        
        pagamentoService.atualizarStatusById(pagamentoId, statusNovo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/pagamento/{pagamentoId}")
    @Transactional
    public ResponseEntity<Pagamento> desativarPagamento(@PathVariable Long pagamentoId, @RequestBody Pagamento.StatusPagamento statusNovo){
        
        pagamentoService.desativarPagamentoById(pagamentoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
