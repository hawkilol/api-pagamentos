package com.kalil.api_pagamentos.v1.dto;

import com.kalil.api_pagamentos.domain.model.MetodoPagamento;
import com.kalil.api_pagamentos.domain.model.Pagamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
public class PagamentoIn {
    private String codigoDebito;
    private String cpfCnpj;
    private MetodoPagamento metodoPagamento;
    private double valor;
    // private Pagamento.StatusPagamento status;
    private Integer nCartao;

    
}
