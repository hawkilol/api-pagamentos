package com.kalil.api_pagamentos.v1.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.kalil.api_pagamentos.v1.dto.StatusPagamentoIn;
import com.kalil.api_pagamentos.v1.dto.StatusPagamentoOut;

import io.micrometer.common.lang.NonNull;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @NonNull
    private ModelMapper modelMapper;

    @PostMapping(value = "/pagamento")
    @Transactional
    public ResponseEntity<Pagamento> criarPag(@Valid @RequestBody PagamentoIn pagamentoIn){
        Pagamento pagamento = pagamentoService.criar(pagamentoIn);
        return new ResponseEntity<>(pagamento, HttpStatus.CREATED);

    }
    @GetMapping(value = "/pagamento/{pagamentoId}")
    @Transactional
    public ResponseEntity<Pagamento> lerPag(@PathVariable Long pagamentoId){
        Pagamento pagamento = pagamentoService.read(pagamentoId);
        return new ResponseEntity<>(pagamento, HttpStatus.OK);
    }
    
    @PostMapping(value = "/pagamentos-filtar", params = {"size", "page"})
    @Transactional
    public ResponseEntity<Page<Pagamento>> listarPorFiltro(@ParameterObject @PageableDefault(page = 0, size = 1)
    Pageable pageable, @RequestBody FiltroPagamentoIn filtroPagamentoIn){
        
        Page<Pagamento> pagamentos = pagamentoService.listarPorFiltro(filtroPagamentoIn, pageable);
        // Page<PagamentoOut> pagamentosOut = pagamentos.map(fo -> {
        //     return modelMapper.map(fo, PagamentoOut.class);
        // });
        return new ResponseEntity<>(pagamentos, HttpStatus.OK);
    }

    @PutMapping(value = "/pagamento-status/{pagamentoId}")
    @Transactional
    public ResponseEntity<Pagamento> atualizarStatus(@PathVariable Long pagamentoId, @RequestBody StatusPagamentoIn statusNovo){
        Pagamento.StatusPagamento status = Pagamento.StatusPagamento.valueOf(statusNovo.getStatus());
        pagamentoService.atualizarStatusById(pagamentoId, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/pagamento/{pagamentoId}")
    @Transactional
    public ResponseEntity<Pagamento> desativarPagamento(@PathVariable Long pagamentoId){
        
        pagamentoService.desativarPagamentoById(pagamentoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping(value = "/pagamento-status-listar")
    @Transactional
    public ResponseEntity<List<StatusPagamentoOut>> listarStatusPagamentoEnum(){
        
        List<StatusPagamentoOut> status = Arrays.stream(Pagamento.StatusPagamento.values())
        .map(s -> new StatusPagamentoOut(s.name(), s.getDesc())) 
        .collect(Collectors.toList());        
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

}
