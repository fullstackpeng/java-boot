package com.fullstackpeng.common.data.jpa.query.core.handler;

import com.fullstackpeng.common.base.utils.StrUtil;
import com.fullstackpeng.common.data.jpa.query.annotation.Query;
import com.fullstackpeng.common.data.jpa.query.core.AbstractPredicateHandler;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;

@Slf4j
public class QueryPredicateHandler extends AbstractPredicateHandler {
    @Override
    public Class<? extends Annotation> getAnnotation() {
        return Query.class;
    }

    @Override
    public <Z, X> jakarta.persistence.criteria.Predicate buildPredicate(jakarta.persistence.criteria.CriteriaBuilder criteriaBuilder, jakarta.persistence.criteria.From<Z, X> from, String fieldName, Object value, Annotation annotation) {
        boolean isQuery = annotation instanceof Query;
        if (!isQuery) {
            return null;
        }
        if (value == null) {
            return null;
        }
        Query query = (Query) annotation;

        String blurry = query.blurry();
        Query.Join join = query.join();
        String joinName = query.joinName();

        // 模糊多字段查询
        if (StrUtil.isNotBlank(blurry)) {
            return this.buildBlurryPredicate(criteriaBuilder, from, fieldName, value, blurry);
        }

        // 连接查询
        if (StrUtil.isNotBlank(joinName)) {
            jakarta.persistence.criteria.Join<Object, Object> joinObj = switch (join) {
                case RIGHT -> from.join(joinName, jakarta.persistence.criteria.JoinType.RIGHT);
                case INNER -> from.join(joinName, jakarta.persistence.criteria.JoinType.INNER);
                default -> from.join(joinName, jakarta.persistence.criteria.JoinType.LEFT);
            };
            return criteriaBuilder.equal(joinObj.get(fieldName), value);
        }

        return null;
    }


    private <Z, X> jakarta.persistence.criteria.Predicate buildBlurryPredicate(jakarta.persistence.criteria.CriteriaBuilder criteriaBuilder, jakarta.persistence.criteria.From<Z, X> from, String fieldName, Object value, String blurry) {
        String[] fields = blurry.split(",");
        jakarta.persistence.criteria.Predicate[] predicates = new jakarta.persistence.criteria.Predicate[fields.length];
        for (int i = 0; i < fields.length; i++) {
            predicates[i] = criteriaBuilder.like(from.get(fields[i]).as(String.class), "%" + value + "%");
        }
        return criteriaBuilder.or(predicates);
    }
}
