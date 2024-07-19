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
public class PermissionQuery extends BaseQuery {
    /**
     * 菜单名称
     */
    @Like
    @Schema(description = "菜单名称")
    private String title;

    /**
     * 父级id
     */
    @Equals(allowNull = true)
    @Schema(description = "父级id")
    private String parentId;
}
