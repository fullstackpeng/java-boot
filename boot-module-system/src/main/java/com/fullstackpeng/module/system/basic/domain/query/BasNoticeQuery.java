package com.fullstackpeng.module.system.basic.domain.query;

import com.fullstackpeng.common.api.BaseQuery;
import com.fullstackpeng.common.data.jpa.query.core.Equals;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BasNoticeQuery extends BaseQuery {
    @Schema(hidden = true)
    private String userId;
    /**
     * 商户ID
     */
    @Equals(allowNull = true)
    @Schema(hidden = true)
    private String orgId;


    @Equals
    @Schema(hidden = true)
    private Boolean enabled;
}
