package com.fullstackpeng.common.security.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 登录请求
 */
@Getter
@Setter
public class LoginRequest implements Serializable {
    @Schema(description = "用户名", requiredMode = Schema.RequiredMode.REQUIRED, hidden = true)
    private String username;
    @NotBlank(message = "密码不能为空")
    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
