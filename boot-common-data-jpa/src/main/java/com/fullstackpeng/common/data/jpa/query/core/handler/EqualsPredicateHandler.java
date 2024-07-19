package com.fullstackpeng.common.data.jpa.query.core.handler;

import com.fullstackpeng.common.data.jpa.query.core.Equals;
import com.fullstackpeng.common.data.jpa.query.core.AbstractPredicateHandler;
import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;

/**
 * 构建“等于条件”({@code field = 'xxx'})场景的 {@link Predicate} 处理器.
 */
@Slf4j
public class EqualsPredicateHandler extends AbstractPredicateHandler {
    @Override
    public Class<? extends Annotation> getAnnotation() {
        return Equals.class;
    }

    @Override
    public <Z, X> Predicate buildPredicate(CriteriaBuilder criteriaBuilder, From<Z, X> from, String fieldName,
                                           @Nullable Object value, @Nullable Annotation annotation) {
        if (null != annotation) {
            Equals equals = (Equals) annotation;
            // 是否允许查询null
            if (value == null && equals.allowNull()) {
                return criteriaBuilder.and(super.buildIsNullPredicate(criteriaBuilder, from, fieldName));
            }
        }
        if (null == value) {
            return null;
        }
        return criteriaBuilder.and(super.buildEqualsPredicate(criteriaBuilder, from, fieldName, value));
    }
}
