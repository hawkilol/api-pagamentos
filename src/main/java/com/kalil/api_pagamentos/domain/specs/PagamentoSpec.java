package com.kalil.api_pagamentos.domain.specs;

import org.springframework.data.jpa.domain.Specification;

import com.kalil.api_pagamentos.domain.model.Pagamento;
import com.kalil.api_pagamentos.v1.dto.FiltroPagamento;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class PagamentoSpec {
    
    public Predicate addPredicate(Predicate predicate, Root<Pagamento> root, CriteriaBuilder criteriaBuilder, FiltroPagamento filtro, String atributeName) {
        if (filtro.getCodigoDebito() != null && !filtro.getCodigoDebito().isEmpty()){
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get(atributeName), filtro.getCodigoDebito()));
        }
        return predicate;
    }

    public Specification<Pagamento> filtro(FiltroPagamento filtro) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            predicate = addPredicate(predicate, root, criteriaBuilder, filtro, "codigoDebito");
            predicate = addPredicate(predicate, root, criteriaBuilder, filtro, "cpfCnpj");
            predicate = addPredicate(predicate, root, criteriaBuilder, filtro, "status");

            return predicate;
        };
    }
}
