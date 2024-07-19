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
public class QuartzJobQuery extends BaseQuery {
    @Like
    @Schema(description = "任务名称")
    private String jobName;

    @Equals
    @Schema(description = "任务分组/商户识别码")
    private String sysCode;
    @Equals
    @Schema(description = "是否启用")
    private Boolean enabled;
}
