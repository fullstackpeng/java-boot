package com.fullstackpeng.module.system.sys.domain.query;

import com.fullstackpeng.common.api.BaseQuery;
import com.fullstackpeng.common.data.jpa.query.core.Equals;
import com.fullstackpeng.common.data.jpa.query.core.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TenantQuery extends BaseQuery {
    /**
     * 厂商名称
     */
    @Like
    @Schema(description = "厂商名称")
    private String name;
    /**
     * 厂商代码
     */
    @Equals
    @Schema(description = "厂商代码")
    private String sysCode;

    /**
     * 机构类型
     * 1 厂商
     */
    @Schema(hidden = true)
    @Equals
    private Integer type = 1;

    /**
     * 是否启用
     */
    @Equals
    @Schema(description = "是否启用")
    public Boolean enabled;
}
