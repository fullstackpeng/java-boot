package com.fullstackpeng.module.system.basic.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BasUserRestPasswordDto implements Serializable {
    /**
     * id
     */
    @Schema(description = "用户ID")
    @NotBlank(message = "用户ID不能为空")
    private String id;
    /**
     * 密码
     */
    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;
    /**
     * 操作人
     */
    @Schema(description = "操作人", hidden = false)
    private String operator;
}
