package com.fullstackpeng.module.system.sys.domain.query;

import com.fullstackpeng.common.api.BaseQuery;
import com.fullstackpeng.common.data.jpa.query.core.Equals;
import com.fullstackpeng.common.data.jpa.query.core.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class DictQuery extends BaseQuery {

    /**
     * 名称
     */
    @Like
    @Schema(description = "名称")
    private String name;

    /**
     * 类型
     */
    @Equals
    @Schema(description = "类型")
    private String type;


    /**
     * 状态
     */
    @Equals
    @Schema(description = "状态")
    private Boolean enabled;
}
