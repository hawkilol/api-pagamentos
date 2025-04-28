package com.kalil.api_pagamentos.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class StatusPagamentoIn {
    @Schema(example = "SUCESSO")
    private String status;
}
