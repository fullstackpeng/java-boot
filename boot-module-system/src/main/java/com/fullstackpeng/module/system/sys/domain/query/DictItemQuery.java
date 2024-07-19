package com.fullstackpeng.module.system.sys.domain.query;

import com.fullstackpeng.common.api.BaseQuery;
import com.fullstackpeng.common.data.jpa.query.core.Equals;
import com.fullstackpeng.common.data.jpa.query.core.Like;
import com.fullstackpeng.common.data.jpa.query.annotation.Query;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class DictItemQuery extends BaseQuery {

    /**
     * 字典id
     */
    @Query(value = "id", joinName = "dict")
    @Schema(description = "字典id")
    private String dictId;

    /**
     * 字典类型
     */
    @Equals
    @Schema(description = "字典类型")
    private String dictType;

    /**
     * 字典标签
     */
    @Like
    @Schema(description = "字典标签")
    private String label;

    /**
     * 状态
     */
    @Equals
    @Schema(description = "状态")
    private Boolean enabled;
}
