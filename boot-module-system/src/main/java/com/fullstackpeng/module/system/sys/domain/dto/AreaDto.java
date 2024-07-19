package com.fullstackpeng.module.system.sys.domain.dto;

import com.fullstackpeng.common.api.domain.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class AreaDto extends BaseDto {
    @Schema(description = "id")
    private String id;
    /**
     * 父级id
     */
    @Schema(description = "父级id")
    private String parentId;
    @Schema(description = "地区编码")
    @NotBlank(message = "地区编码不能为空")
    private String code;
    @Schema(description = "地区名称")
    @NotBlank(message = "地区名称不能为空")
    private String name;
    /**
     * 全称
     */
    @Schema(description = "全称")
    private String fullName;
    /**
     * 树路径
     */
    @Schema(description = "树路径")
    private String path;
    /**
     * 级别
     */
    @Schema(description = "级别")
    private Integer level;

    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    private Boolean enabled;
}
