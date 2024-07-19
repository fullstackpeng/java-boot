package com.fullstackpeng.common.data.jpa.query.core;

import java.lang.annotation.*;

/**
 * 用于“是 NULL”({@code field IS NULL})条件场景的注解.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IsNull {
    /**
     * 注解的实体字段属性名称，默认为空或空字符串时将使用属性名称.
     *
     * @return 字符串值
     */
    String value() default "";

    /**
     * 是否使用驼峰命名，默认为 {@code true}.
     *
     * @return 值
     */
    boolean underCamel() default true;
}
