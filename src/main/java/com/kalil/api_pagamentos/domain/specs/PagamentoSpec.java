package com.kalil.api_pagamentos.domain.specs;

import org.springframework.data.jpa.domain.Specification;

import com.kalil.api_pagamentos.domain.model.Pagamento;
import com.kalil.api_pagamentos.v1.dto.FiltroPagamento;

import jakarta.persistence.criteria.Predicate;

public class PagamentoSpec {
    public static Specification<Pagamento> filtro(FiltroPagamento filtro) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (filtro.getCodigoDebito() != null && !filtro.getCodigoDebito().isEmpty()){
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("codigoDebito"), filtro.getCodigoDebito()));

            }

            return predicate;
        };
    }
}
