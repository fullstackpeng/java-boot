package com.fullstackpeng.module.system.common.domain.dto;

import com.fullstackpeng.common.api.domain.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 附件
 */
@Getter
@Setter
@Accessors(chain = true)
public class AttachmentDto extends BaseDto {
    @Schema(description = "附件id")
    private String id;
    /**
     * 文件名
     */
    @Schema(description = "文件名")
    @NotBlank(message = "文件名不能为空")
    private String displayName;
    /**
     * 文件类型
     */
    @Schema(description = "文件类型")
    @NotBlank(message = "文件类型不能为空")
    private String mediaType;
    /**
     * 文件大小
     */
    @Schema(description = "文件大小")
    private Long size;
    /**
     * 链接
     */
    @Schema(description = "链接")
    @NotBlank(message = "链接不能为空")
    private String permalink;
    /**
     * 归属商户
     */
    @Schema(description = "归属商户")
    private String sysCode;
}
