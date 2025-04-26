package com.kalil.api_pagamentos.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertTrue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "PAGAMENTO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Pagamento {

    @Id
    @Column(name = "pag_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "pag_cod_debito", nullable = false)
    private String codigoDebito;


    @Column(name = "pag_cpf_cnpj", nullable = false)
    private String cpfCnpj;

    @ManyToOne
    @JoinColumn(name = "pag_metpag", nullable = false)
    private MetodoPagamento metodoPagamento;

    
    @Column(name = "pag_valor", nullable = false)
    private double valor;
    
    
    public enum StatusPagamento {
        PENDENTE,
        SUCESSO,
        FALHA
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "pag_status")
    private StatusPagamento status;
    
    @Column(name = "pag_ativo")
    private Boolean ativo;

    @Column(name = "pag_ncartao")
    private Integer ncartao;


    @AssertTrue(message = "Número do cartão é obrigatório para pagamentos com o cartão de crédito!")
    public Boolean isNcartaoValido(){
        if (metodoPagamento.getNome().equalsIgnoreCase("cartao_credito") || 
        metodoPagamento.getNome().equalsIgnoreCase("cartao_debito")){
            return ncartao != null;
        }
        return true;
    }
}
