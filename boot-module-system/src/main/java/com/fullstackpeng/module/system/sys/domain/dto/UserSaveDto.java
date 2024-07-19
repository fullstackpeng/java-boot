package com.fullstackpeng.module.system.sys.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;


@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserSaveDto extends UserDto {
    /**
     * 角色id
     */
    @Schema(description = "角色id")
    private List<RoleSmallDto> roles;
}
