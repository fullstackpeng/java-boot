package com.fullstackpeng.module.system.sys.domain.query;

import com.fullstackpeng.common.api.BaseQuery;
import com.fullstackpeng.common.data.jpa.query.core.Equals;
import com.fullstackpeng.common.data.jpa.query.core.Like;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserQuery extends BaseQuery {
    /**
     * 用户账号
     */
    @Equals
    @Parameter(description = "用户账号")
    private String username;
    /**
     * 用户昵称
     */
    @Like
    @Parameter(description = "用户昵称")
    private String nickname;

    /**
     * 是否启用
     */
    @Equals
    @Parameter(description = "是否启用")
    private Boolean enabled;
}
