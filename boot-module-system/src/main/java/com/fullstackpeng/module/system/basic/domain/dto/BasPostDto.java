package com.fullstackpeng.module.system.basic.domain.dto;

import com.fullstackpeng.common.api.domain.BaseTenantDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class BasPostDto extends BaseTenantDto {
    @Schema(description = "岗位id")
    private String id;
    /**
     * 岗位编码
     */
    @Schema(description = "岗位编码")
    private String code;
    /**
     * 岗位名称
     */
    @Schema(description = "岗位名称")
    private String name;
    /**
     * 岗位描述
     */
    @Schema(description = "岗位描述")
    private String description;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private Boolean enabled;
}
