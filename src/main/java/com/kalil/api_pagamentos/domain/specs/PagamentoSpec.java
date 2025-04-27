package com.kalil.api_pagamentos.domain.specs;

import org.springframework.data.jpa.domain.Specification;

import com.kalil.api_pagamentos.domain.model.Pagamento;
import com.kalil.api_pagamentos.v1.dto.FiltroPagamento;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class PagamentoSpec {
    
    public Specification<Pagamento> filtro(FiltroPagamento filtro) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
    
            predicate = addPredicate(filtro.getCodigoDebito(), "codigoDebito", root, criteriaBuilder, predicate);
            predicate = addPredicate(filtro.getCpfCnpj(), "cpfCnpj", root, criteriaBuilder, predicate);
            predicate = addPredicate(filtro.getStatus(), "status", root, criteriaBuilder, predicate);
    
            return predicate;
        };
    }
    
    private Predicate addPredicate(Object value, String fieldName, Root<Pagamento> root, CriteriaBuilder criteriaBuilder, Predicate predicate) {
        if (value != null && !value.toString().isEmpty()) {
            predicate = criteriaBuilder.and(predicate,
                    criteriaBuilder.equal(root.get(fieldName), value));
        }
        return predicate;
    }
    
}
