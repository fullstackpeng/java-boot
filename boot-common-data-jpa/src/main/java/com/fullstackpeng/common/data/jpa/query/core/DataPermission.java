package com.fullstackpeng.common.data.jpa.query.core;

import java.lang.annotation.*;

/**
 * 数据权限,用于过滤数据,
 * 1. 如果没有用到 {@code @OneToOne}关联关系，只需要填写{@code field}即可,
 * 2. 如果用到了{@code @OneToOne}关联关系，需要填写{@code field}和{@code joinName},举例：应该是 {@code @DataPermission(joinName = "dept", fieldName = "id")}
 */
@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataPermission {
    /**
     * 数据权限字段,在JPA中表示在ENTITY中的字段
     */
    String field() default "";

    /**
     * 关联表/字段,在JPA中表示Entity的字段
     */
    String joinName() default "";

}
