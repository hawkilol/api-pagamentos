package com.kalil.api_pagamentos.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kalil.api_pagamentos.domain.model.Pagamento;
import com.kalil.api_pagamentos.domain.repository.PagamentoRepository;
import com.kalil.api_pagamentos.domain.specs.PagamentoSpec;
import com.kalil.api_pagamentos.v1.dto.FiltroPagamento;
import com.kalil.api_pagamentos.v1.dto.PagamentoIn;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PagamentoService {
    

    private final PagamentoRepository pagamentoRepository;
     
    //add sort
    //maybe add generic spec?
    public Page<Pagamento> pesquisar(Pageable pageable){
        return pagamentoRepository.findAll(pageable);
    }
    // public Page<Pagamento> pesquisarPorCpfCnpj(Pageable pageable, String cpf_cnpj){
    //     return pagamentoRepository.findAllByCpfCnpj(cpf_cnpj);
    // }
    // public List<Pagamento> pesquisarPorCpfCnpj(String cpf_cnpj){
    //     return pagamentoRepository.findAllByCpfCnpj(cpf_cnpj);
    //     return pagamentoRepository.find
    // }
    
    public List<Pagamento> listarTodos(PagamentoIn pagamentoIn){
        return pagamentoRepository.findAll();
    }

    public List<Pagamento> listarPorCodigoDebito(String codigoDebito){
        return pagamentoRepository.findAllByCodigoDebito(codigoDebito);
    }
    public List<Pagamento> listarPorCpfCnpj(String cpfCnpj){
        return pagamentoRepository.findAllByCpfCnpj(cpfCnpj);
    }
    public List<Pagamento> listarPorStatus(Pagamento.StatusPagamento status){
        return pagamentoRepository.findAllByStatus(status);
    }

    public Page<Pagamento> listarPorFiltro(FiltroPagamento pagamentoFiltro, Pageable pageable){
        return pagamentoRepository.findAll(PagamentoSpec.filtro(pagamentoFiltro), pageable);
    }
}
