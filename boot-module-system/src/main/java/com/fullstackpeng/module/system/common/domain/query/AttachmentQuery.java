package com.fullstackpeng.module.system.common.domain.query;

import com.fullstackpeng.common.api.BaseQuery;
import com.fullstackpeng.common.data.jpa.query.core.Equals;
import com.fullstackpeng.common.data.jpa.query.core.Like;
import com.fullstackpeng.common.data.jpa.query.core.LikeRight;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 附件查询
 */
@Getter
@Setter
@Accessors(chain = true)
public class AttachmentQuery extends BaseQuery {
    @Schema(description = "文件名")
    @LikeRight
    private String displayName;

    /**
     * 文件类型
     */
    @Schema(description = "文件类型")
    @Like
    private String mediaType;
    /**
     * 商户号
     */
    @Schema(description = "商户号", hidden = true)
    @Equals(allowNull = true)
    private String sysCode;
}
