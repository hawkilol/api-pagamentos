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
     
    public Pagamento criar(PagamentoIn pagamentoIn) {
        try {
            Pagamento pagamento = new Pagamento();
    
            pagamento.setCodigoDebito(pagamentoIn.getCodigoDebito());
            pagamento.setCpfCnpj(pagamentoIn.getCpfCnpj());
            pagamento.setMetodoPagamento(pagamentoIn.getMetodoPagamento());
            pagamento.setValor(pagamentoIn.getValor());
            pagamento.setStatus(Pagamento.StatusPagamento.PENDENTE);
            pagamento.setNCartao(pagamentoIn.getNCartao());
            pagamento.setAtivo(true);  
    
            return pagamentoRepository.save(pagamento);
    
        } catch (Exception e) {    
            throw new RuntimeException("Erro ao criar pagamento", e);
        }
    }
    

    public Boolean statusAtualizarPermitido(Pagamento pagamento, Pagamento.StatusPagamento statusNovo){
        Pagamento.StatusPagamento statusAtual = pagamento.getStatus(); 
    
        if (statusAtual == Pagamento.StatusPagamento.PENDENTE){
            return true;
        }
        if (statusAtual == Pagamento.StatusPagamento.SUCESSO){
            return false;
        }
        if (statusAtual == Pagamento.StatusPagamento.FALHA){
            return statusNovo == Pagamento.StatusPagamento.PENDENTE;
        }
        return false;
    }

    public void atualizarStatusById(Long pagamentoId, Pagamento.StatusPagamento statusNovo){
        try {
            Pagamento pagamento = pagamentoRepository.findById(pagamentoId).orElse(null);
            if (statusAtualizarPermitido(pagamento, statusNovo)){
                pagamento.setStatus(statusNovo);
            }
            else{
                throw new Exception(String.format("%s, o status não pode ser alterado para %s", pagamento.getStatus().getDesc(), statusNovo.getDesc()));   
            }
        
            pagamentoRepository.save(pagamento);
        } catch (Exception e) {
        }
    }

    public void desativarPagamentoById(Long pagamentoId){
        try {
            Pagamento pagamento = pagamentoRepository.findById(pagamentoId).orElse(null);
            if (pagamento.getStatus() == Pagamento.StatusPagamento.PENDENTE){
                pagamento.setAtivo(false);
            }
            else{
                throw new Exception("O Pagamento deve estar Pendente para ser desativado");
            }
            pagamentoRepository.save(pagamento);
        } catch (Exception e) {
        }
    }
    public void ativarPagamentoById(Long pagamentoId){
        try {
            Pagamento pagamento = pagamentoRepository.findById(pagamentoId).orElse(null);
            pagamento.setAtivo(true);
            pagamentoRepository.save(pagamento);
        } catch (Exception e) {
        }
    }

    //add sort
    public Page<Pagamento> pesquisar(Pageable pageable){
        return pagamentoRepository.findAll(pageable);
    }

    public Page<Pagamento> listarPorFiltro(FiltroPagamento filtroPagamento, Pageable pageable){
        PagamentoSpec pagamentoSpec = new PagamentoSpec();
        return pagamentoRepository.findAll(pagamentoSpec.filtro(filtroPagamento), pageable);
    }
    
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
}
