package com.kalil.api_pagamentos.v1.dto;

import com.kalil.api_pagamentos.domain.model.Pagamento;

import lombok.Data;

@Data
public class FiltroPagamento {
    private String codigoDebito;
    private String cpfCnpj;
    private Pagamento.StatusPagamento status;
}
