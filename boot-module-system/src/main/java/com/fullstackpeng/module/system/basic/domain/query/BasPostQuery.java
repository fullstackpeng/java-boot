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
public class BasPostQuery extends BaseQuery {

    @Equals
    @Schema(description = "岗位编码")
    private String code;

    @Like
    @Schema(description = "岗位名称")
    private String name;

    @Equals
    @Schema(description = "状态")
    private Boolean enabled;
}
