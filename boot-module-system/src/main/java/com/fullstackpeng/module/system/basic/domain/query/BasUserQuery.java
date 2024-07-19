package com.fullstackpeng.module.system.basic.domain.query;

import com.fullstackpeng.common.api.BaseQuery;
import com.fullstackpeng.common.base.utils.StrUtil;
import com.fullstackpeng.common.data.jpa.query.core.Equals;
import com.fullstackpeng.common.data.jpa.query.core.Like;
import com.fullstackpeng.common.data.jpa.query.annotation.Query;
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
public class BasUserQuery extends BaseQuery {
    /**
     * 商户
     */
    @Schema(description = "商户", hidden = true)
    @Equals
    private String sysCode;
    /**
     * 用户名
     */
    @Schema(description = "用户名")
    @Equals
    private String username;
    /**
     * 昵称
     */
    @Schema(description = "昵称")
    @Like
    private String nickname;
    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    @Equals
    private String phone;

    /**
     * 岗位ID
     */
    @Query(value = "id", joinName = "posts")
    @Schema(description = "岗位id")
    private String postId;
    /**
     * 状态
     */
    @Equals
    @Schema(description = "状态")
    private Boolean enabled;

    /**
     * 机构ID
     */
    @Schema(description = "机构id")
    @Query(value = "id", joinName = "org")
    private String orgId;

    public String getOrgId() {
        if (StrUtil.isBlank(orgId)) {
            return null;
        }
        return orgId;
    }
}
