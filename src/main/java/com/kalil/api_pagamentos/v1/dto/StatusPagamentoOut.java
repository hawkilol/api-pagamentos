package com.kalil.api_pagamentos.v1.dto;

import lombok.Data;

@Data
public class StatusPagamentoOut {
    private String name;
    private String desc;

    public StatusPagamentoOut(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
