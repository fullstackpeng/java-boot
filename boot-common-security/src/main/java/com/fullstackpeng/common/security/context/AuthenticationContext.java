package com.fullstackpeng.common.security.context;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationContext {
    /**
     * 用户名
     */
    private String username;
    /**
     * 是否租户登录，租户登录的用户为管理员，非租户登录的用户为普通用户
     */
    private Boolean tenantLogin;
}
