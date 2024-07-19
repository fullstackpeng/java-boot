package com.fullstackpeng.common.data.jpa.query.core.handler;

import com.fullstackpeng.common.base.context.UserContext;
import com.fullstackpeng.common.base.context.UserInfo;
import com.fullstackpeng.common.base.exception.QueryException;
import com.fullstackpeng.common.base.utils.CollectionUtil;
import com.fullstackpeng.common.base.utils.StrUtil;
import com.fullstackpeng.common.data.jpa.query.core.AbstractPredicateHandler;
import com.fullstackpeng.common.data.jpa.query.core.DataPermission;
import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 数据权限处理
 */
public class DataPermissionPredicateHandler extends AbstractPredicateHandler {
    @Override
    public Class<? extends Annotation> getAnnotation() {
        return DataPermission.class;
    }

    @Override
    public <Z, X> Predicate buildPredicate(CriteriaBuilder criteriaBuilder, From<Z, X> from, String fieldName, @Nullable Object value, @Nullable Annotation annotation) {
        DataPermission dataPermission = (DataPermission) annotation;
        if (null == dataPermission) {
            return null;
        }
        //拿取当前用户上下文
        UserInfo userInfo = UserContext.get();
        if (null == userInfo) {
            throw new QueryException("数据权限处理异常!,用户上下文为空");
        }
        // 获取当前用户的数据权限
        List<String> dataScopes = userInfo.getDataScopes();
        if (CollectionUtil.isNotEmpty(dataScopes)) {
            if (StrUtil.isBlank(dataPermission.field())) {
                throw new QueryException("数据权限处理异常!,数据权限字段为空");
            }
            if (StrUtil.isNotBlank(dataPermission.joinName())) {
                return criteriaBuilder.and(
                        from.join(dataPermission.joinName()).get(dataPermission.field()).in(dataScopes)
                );
            } else {
                return criteriaBuilder.and(
                        from.get(dataPermission.field()).in(dataScopes)
                );
            }
        }
        return null;
    }
}
