package com.fullstackpeng.module.system.basic.domain.query;

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
public class BasOrgQuery extends BaseQuery {
    /**
     * 父级机构ID
     */
    @Equals(allowNull = true)
    @Schema(description = "父级机构ID")
    private String parentId;
    /**
     * 机构名称
     */
    @Like
    @Schema(description = "机构名称")
    private String name;
    /**
     * 联系人
     */
    @Like
    @Schema(description = "联系人")
    private String linkMan;
    /**
     * 联系电话
     */
    @Like
    @Schema(description = "联系电话")
    private String linkTel;
    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    @Equals
    private Boolean enabled;
    /**
     * 系统编码
     */
    @Equals
    @Schema(description = "系统编码", hidden = true)
    private String sysCode;
}
