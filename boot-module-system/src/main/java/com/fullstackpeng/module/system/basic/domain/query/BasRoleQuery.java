package com.fullstackpeng.module.system.basic.domain.query;

import com.fullstackpeng.common.api.BaseQuery;
import com.fullstackpeng.common.data.jpa.query.core.Equals;
import com.fullstackpeng.common.data.jpa.query.core.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString
public class BasRoleQuery extends BaseQuery {
    /**
     * 角色名称
     */
    @Like
    @Schema(description = "角色名称")
    private String name;
    /**
     * 角色标识
     */
    @Equals
    @Schema(description = "角色标识")
    private String code;
    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    @Equals
    private Boolean enabled;
    /**
     * 商户识别码
     */
    @Equals
    @Schema(description = "商户识别码", hidden = true)
    private String sysCode;
}
