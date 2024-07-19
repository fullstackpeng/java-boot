package com.fullstackpeng.module.system.basic.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@Data
@EqualsAndHashCode
public class RoleSmallDto implements Serializable {
    @Schema(description = "角色ID")
    private String id;
    @Schema(description = "角色名称")
    private String name;
}
