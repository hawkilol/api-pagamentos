package com.kalil.api_pagamentos.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.kalil.api_pagamentos.domain.model.Pagamento;


@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long>, JpaSpecificationExecutor<Pagamento>{
    List<Pagamento> findAllByCodigoDebito(String codigoDebito);
    List<Pagamento> findAllByCpfCnpj(String cpfCnpj);
    List<Pagamento> findAllByStatus(Pagamento.StatusPagamento status);

    List<Pagamento> findAllByCodigoDebitoAndCpfCnpjAndStatus(String codigoDebito, String cpfCnpj, Pagamento.StatusPagamento status);
    
}
