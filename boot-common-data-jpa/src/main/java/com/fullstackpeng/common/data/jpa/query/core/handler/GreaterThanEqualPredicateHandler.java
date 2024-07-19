package com.fullstackpeng.common.data.jpa.query.core.handler;

import com.fullstackpeng.common.data.jpa.query.core.GreaterThanEqual;
import com.fullstackpeng.common.data.jpa.query.core.AbstractPredicateHandler;
import jakarta.annotation.Nonnull;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;

/**
 * 构建“大于等于条件”({@code field >= 'xxx'})场景的 {@link Predicate} 处理器.
 */
public class GreaterThanEqualPredicateHandler extends AbstractPredicateHandler {

    @Override
    public Class<GreaterThanEqual> getAnnotation() {
        return GreaterThanEqual.class;
    }


    @Override
    public <Z, X> Predicate _buildPredicate(CriteriaBuilder criteriaBuilder, From<Z, X> from, String fieldName,
                                            @Nonnull Object value) {
        return criteriaBuilder.and(
                super.buildGreaterThanEqualPredicate(criteriaBuilder, from, fieldName, value)
        );
    }
}
