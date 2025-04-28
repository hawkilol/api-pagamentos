package com.kalil.api_pagamentos;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kalil.api_pagamentos.domain.model.MetodoPagamento;
import com.kalil.api_pagamentos.domain.model.Pagamento;
import com.kalil.api_pagamentos.v1.controller.PagamentoController;
import com.kalil.api_pagamentos.v1.dto.FiltroPagamentoIn;
import com.kalil.api_pagamentos.v1.dto.PagamentoIn;
import com.kalil.api_pagamentos.v1.dto.StatusPagamentoIn;
import com.kalil.api_pagamentos.v1.dto.StatusPagamentoOut;

@SpringBootTest
class ApiPagamentosApplicationTests {

    @Autowired
    private PagamentoController pagamentoController;

    // Teste de Criação de Pagamento
    @Test
    void deveCriarPagamentoComSucesso() throws Exception {
        PagamentoIn pagamentoIn = new PagamentoIn();
        pagamentoIn.setCodigoDebito("777777");
        pagamentoIn.setCpfCnpj("212.644.657-34");
        MetodoPagamento met = new MetodoPagamento();
        met.setId(3L);
        pagamentoIn.setMetodoPagamento(met);
        pagamentoIn.setValor(1337.10);
        pagamentoIn.setNCartao("5131117859802111");
        System.out.println("pagamentoIn");
        System.out.println(pagamentoIn);
        ResponseEntity<Pagamento> response = pagamentoController.criarPag(pagamentoIn);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    // Teste de Leitura de Pagamento
    @Test
    void deveLerPagamentoComSucesso() throws Exception {
        Long pagamentoId = 1L;
        ResponseEntity<Pagamento> response = pagamentoController.lerPag(pagamentoId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    // Teste de Listagem de Pagamentos com Filtro
    @Test
    void deveListarPagamentosComFiltro() throws Exception {
        FiltroPagamentoIn filtroPagamentoIn = new FiltroPagamentoIn();
        filtroPagamentoIn.setCodigoDebito("777777");
        filtroPagamentoIn.setCpfCnpj("212.644.657-34");
        filtroPagamentoIn.setStatus("PENDENTE");

        ResponseEntity<Page<Pagamento>> response = pagamentoController.listarPorFiltro(Pageable.unpaged(), filtroPagamentoIn);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    // Teste de Atualização de Status do Pagamento
    @Test
    void deveAtualizarStatusParaProcessadoComSucesso() throws Exception {
        Long pagamentoId = 1L;
        StatusPagamentoIn statusNovo = new StatusPagamentoIn();
        statusNovo.setStatus("SUCESSO");

        ResponseEntity<Pagamento> response = pagamentoController.atualizarStatus(pagamentoId, statusNovo);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    // Teste de Exclusão Lógica de Pagamento
    @Test
    void deveDesativarPagamentoComStatusPendente() throws Exception {
        Long pagamentoId = 1L;
        ResponseEntity<Pagamento> response = pagamentoController.desativarPagamento(pagamentoId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    // Teste de Listagem de Status de Pagamento
    @Test
    void deveListarTodosOsStatusDePagamento() throws Exception {
        ResponseEntity<List<StatusPagamentoOut>> response = pagamentoController.listarStatusPagamentoEnum();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody()).contains(new StatusPagamentoOut("PENDENTE", "Pagamento pendente de processamento"));
        assertThat(response.getBody()).contains(new StatusPagamentoOut("SUCESSO", "Pagamento processado com sucesso"));
        assertThat(response.getBody()).contains(new StatusPagamentoOut("FALHA", "Pagamento processado com falha"));
    }
}
