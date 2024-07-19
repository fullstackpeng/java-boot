package com.fullstackpeng.module.system.basic.domain.dto;

import com.fullstackpeng.common.api.domain.BaseTenantDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;


@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString
public class BasRoleDto extends BaseTenantDto {
    /**
     * 角色名称
     */
    @Schema(description = "id")
    private String id;
    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String name;
    /**
     * 角色标识
     */
    @Schema(description = "角色标识")
    private String code;
    /**
     * 数据范围
     */
    @Schema(description = "数据范围")
    private Integer dataScope;
    /**
     * 自定义数据范围
     */
    @Schema(description = "自定义数据范围")
    private List<String> orgIds;
    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;
    /**
     * 是否系统
     */
    @Schema(description = "是否系统")
    private Boolean system;
    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    private Boolean enabled;

    /**
     * 权限id
     */
    @Schema(description = "权限id")
    private List<String> permissionIds;

}
