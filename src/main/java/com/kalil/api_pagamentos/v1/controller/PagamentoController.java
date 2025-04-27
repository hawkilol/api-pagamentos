package com.kalil.api_pagamentos.v1.controller;

import org.modelmapper.ModelMapper;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kalil.api_pagamentos.domain.model.Pagamento;
import com.kalil.api_pagamentos.domain.service.PagamentoService;
import com.kalil.api_pagamentos.v1.dto.FiltroPagamentoIn;
import com.kalil.api_pagamentos.v1.dto.PagamentoIn;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    private ModelMapper modelMapper;

    @PostMapping(value = "/pagamento")
    @Transactional
    public ResponseEntity<Pagamento> criarPag(@Valid @RequestBody PagamentoIn pagamentoIn){

        Pagamento pagamento = pagamentoService.criar(pagamentoIn);
        // return new ResponseEntity<>(pagamento, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    
    @PostMapping(value = "/pagamentos-listar", params = {"size", "page"})
    @Transactional
    public ResponseEntity<Page<Pagamento>> listarPorFiltro(@ParameterObject @PageableDefault(page = 0, size = 1)
    Pageable pageable, @RequestBody FiltroPagamentoIn filtroPagamentoIn){
        
        Page<Pagamento> pagamentos = pagamentoService.listarPorFiltro(filtroPagamentoIn, pageable);
        // Page<PagamentoOut> pagamentosOut = pagamentos.map(fo -> {
        //     return modelMapper.map(fo, PagamentoOut.class);
        // });
        // return new ResponseEntity<>(pagamentosOut, HttpStatus.OK);
        return new ResponseEntity<>(pagamentos, HttpStatus.OK);
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
