package com.kalil.api_pagamentos.domain.model;

import java.io.Serializable;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

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
@FilterDef(name = "ativoFilter", parameters = @ParamDef(name = "ativo", type = Boolean.class))
@Filters({@Filter(name = "ativoFilter", condition = "pag_ativo = :ativo")})
public class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pag_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
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
        PENDENTE("Pagamento pendente de processamento"),
        SUCESSO("Pagamento processado com sucesso"),
        FALHA("Pagamento processado com falha");
        
        private final String desc;

        private StatusPagamento(String desc){
            this.desc = desc;
        }
        public String getDesc(){
            return desc;
        }
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "pag_status", nullable = false)
    private StatusPagamento status;
    
    
    @Column(name = "pag_ncartao")
    private String nCartao;
    
    @Column(name = "pag_ativo", nullable = false)
    private Boolean ativo;
}