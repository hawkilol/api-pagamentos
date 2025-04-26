package com.kalil.api_pagamentos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.kalil.api_pagamentos.domain.model.MetodoPagamento;


@Repository
public interface MetodoPagamentoRepository extends JpaRepository<MetodoPagamento, Long>, JpaSpecificationExecutor<MetodoPagamento>{
    
}
