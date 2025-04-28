package com.kalil.api_pagamentos.domain.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kalil.api_pagamentos.domain.model.MetodoPagamento;
import com.kalil.api_pagamentos.domain.model.Pagamento;
import com.kalil.api_pagamentos.domain.repository.PagamentoRepository;
import com.kalil.api_pagamentos.domain.specs.PagamentoSpec;
import com.kalil.api_pagamentos.v1.dto.FiltroPagamento;
import com.kalil.api_pagamentos.v1.dto.FiltroPagamentoIn;
import com.kalil.api_pagamentos.v1.dto.PagamentoIn;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PagamentoService {
    
    private final PagamentoRepository pagamentoRepository;
    private final MetodoPagamentoService metodoPagamentoService;
    
    public Pagamento read(@NotNull Long id){
        return pagamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pagamento com id " + id + " não encontrado"));
    }

    public Pagamento criar(PagamentoIn pagamentoIn) {
        try {
            this.validarPagamento(pagamentoIn);
            Pagamento pagamento = new Pagamento();
    
            pagamento.setCodigoDebito(pagamentoIn.getCodigoDebito());
            pagamento.setCpfCnpj(pagamentoIn.getCpfCnpj());
            pagamento.setMetodoPagamento(metodoPagamentoService.read(pagamentoIn.getMetodoPagamento().getId()));
            pagamento.setValor(pagamentoIn.getValor());
            pagamento.setStatus(Pagamento.StatusPagamento.PENDENTE);
            pagamento.setNCartao(pagamentoIn.getNCartao());

            pagamento.setAtivo(true);  
    
            return pagamentoRepository.save(pagamento);
    
        } catch (RuntimeException e) {    
            throw e;
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
            Pagamento pagamento = this.read(pagamentoId);
            if (statusAtualizarPermitido(pagamento, statusNovo)){
                pagamento.setStatus(statusNovo);
            }
            else{
                throw new RuntimeException(String.format("%s, o status não pode ser alterado para %s", pagamento.getStatus().getDesc(), statusNovo.getDesc()));   
            }
            pagamentoRepository.save(pagamento);
        }
        catch (RuntimeException e) {    
            throw e; 
        } 
        catch (Exception e) {
        }
    }

    public void desativarPagamentoById(Long pagamentoId){
        try {
            Pagamento pagamento = this.read(pagamentoId);
            if (pagamento.getStatus() == Pagamento.StatusPagamento.PENDENTE){
                pagamento.setAtivo(false);
                pagamentoRepository.save(pagamento);
            }
            else{
                throw new RuntimeException("O Pagamento deve estar Pendente para ser desativado");
            }
        }
        catch (RuntimeException e) {    
            throw e; 
        }
        catch (Exception e) {
        }
    }
    public void ativarPagamentoById(Long pagamentoId){
        try {
            Pagamento pagamento = this.read(pagamentoId);
            pagamento.setAtivo(true);
            pagamentoRepository.save(pagamento);
        } catch (Exception e) {
        }
    }

    public Page<Pagamento> pesquisar(Pageable pageable){
        return pagamentoRepository.findAll(pageable);
    }

    public Page<Pagamento> listarPorFiltro(FiltroPagamentoIn filtroPagamentoIn, Pageable pageable){
        if (isFiltroVazio(filtroPagamentoIn)) {
            return pagamentoRepository.findAll(pageable);
        }
        PagamentoSpec pagamentoSpec = new PagamentoSpec();
        FiltroPagamento filtroPagamento = new FiltroPagamento();
        filtroPagamento.setCodigoDebito(filtroPagamentoIn.getCodigoDebito());
        filtroPagamento.setCpfCnpj(filtroPagamentoIn.getCpfCnpj());
        filtroPagamento.setStatus(Pagamento.StatusPagamento.valueOf(filtroPagamentoIn.getStatus()));
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
    public void validarPagamento(PagamentoIn pagamentoIn) {
        if (pagamentoIn.getMetodoPagamento() != null && pagamentoIn.getMetodoPagamento().getId() != null) {
            MetodoPagamento metodo = metodoPagamentoService.read(pagamentoIn.getMetodoPagamento().getId());
            if ((metodo.getNome().equalsIgnoreCase("cartao_credito") ||
                metodo.getNome().equalsIgnoreCase("cartao_debito")) && pagamentoIn.getNCartao() == null) {
                    throw new RuntimeException("Número do cartão é obrigatório para pagamentos com o cartão de crédito ou debito!");
            }
        }
    }
    private boolean isFiltroVazio(FiltroPagamentoIn filtroPagamentoIn) {
        return (filtroPagamentoIn.getCodigoDebito() == null || filtroPagamentoIn.getCodigoDebito().isEmpty())
            && (filtroPagamentoIn.getCpfCnpj() == null || filtroPagamentoIn.getCpfCnpj().isEmpty())
            && (filtroPagamentoIn.getStatus() == null || filtroPagamentoIn.getStatus().isEmpty());
    }
}