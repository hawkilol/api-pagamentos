package com.kalil.api_pagamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode;
import org.springframework.web.bind.annotation.RestController;


@EnableSpringDataWebSupport(pageSerializationMode = PageSerializationMode.VIA_DTO)
@SpringBootApplication
@RestController
public class ApiPagamentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPagamentosApplication.class, args);
	}
}
