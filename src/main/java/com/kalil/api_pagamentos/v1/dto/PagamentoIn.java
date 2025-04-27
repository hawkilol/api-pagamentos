package com.kalil.api_pagamentos.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kalil.api_pagamentos.domain.model.MetodoPagamento;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PagamentoIn {

    @Schema(example = "777777")
    @NotNull(message = "Código de débito é obrigatório")
    @Pattern(regexp = "^[0-9]{6}$", message = "Código de débito deve ter 6 dígitos")
    private String codigoDebito;

    @Schema(example = "212.644.657-34")
    @NotNull(message = "CPF ou CNPJ é obrigatório")
    @Pattern(regexp = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2})$", message = "Formato de CPF ou CNPJ inválido")
    private String cpfCnpj;

    @NotNull(message = "Método de pagamento é obrigatório")
    private MetodoPagamento metodoPagamento;

    @Schema(example = "1337.10")
    @Positive(message = "Valor deve ser positivo")
    private double valor;

    @Schema(example = "5131117859802111")
    @JsonProperty("nCartao")
    @Pattern(regexp = "^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14}|6(?:011|5[0-9]{2})[0-9]{12}|3[47][0-9]{13}|(?:2131|1800|35\\d{3})\\d{11})$", message = "Formato do número do cartão de credito ou debito inválido")
    private String nCartao;

    

    @AssertTrue(message = "Número do cartão é obrigatório para pagamentos com o cartão de crédito ou debito!")
    public boolean isNcartaovalTrue() {
        if (metodoPagamento != null && 
            (metodoPagamento.getNome().equalsIgnoreCase("cartao_credito") || 
             metodoPagamento.getNome().equalsIgnoreCase("cartao_debito"))) {
            return nCartao != null && String.valueOf(nCartao).matches("\\d{16}");
        }
        return true;
    }

}
