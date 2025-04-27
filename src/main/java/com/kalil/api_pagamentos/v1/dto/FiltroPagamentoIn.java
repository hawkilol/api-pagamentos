package com.kalil.api_pagamentos.v1.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class FiltroPagamentoIn {

    @Schema(example = "777777")
    @NotNull(message = "Código de débito é obrigatório")
    @Pattern(regexp = "^[0-9]{6}$", message = "Código de débito deve ter 6 dígitos")
    private String codigoDebito;

    @Schema(example = "212.644.657-34")
    @NotNull(message = "CPF ou CNPJ é obrigatório")
    @Pattern(regexp = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2})$", message = "Formato de CPF ou CNPJ inválido")
    private String cpfCnpj;

    @Schema(example = "PENDENTE")
    private String status;
}
