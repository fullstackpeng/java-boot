package com.fullstackpeng.module.system.sys.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fullstackpeng.common.api.domain.BaseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;


@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TenantBasicConfigDto extends BaseDto {
    @Schema(description = "ID")
    @NotBlank(message = "ID不能为空")
    private String id;
    /**
     * 截止使用时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "截止使用时间")
    @NotNull(message = "截止使用时间不能为空")
    private Date usedEndTime;
}
