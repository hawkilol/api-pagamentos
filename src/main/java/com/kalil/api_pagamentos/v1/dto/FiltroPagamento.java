package com.kalil.api_pagamentos.v1.dto;

import lombok.Data;

@Data
public class FiltroPagamento {
    private String codigoDebito;
    private String cpfCnpj;
    private String status;
}
