package com.fullstackpeng.module.system.sys.service.mapstruct;


import com.fullstackpeng.common.base.mapstruct.BaseMapstruct;
import com.fullstackpeng.module.system.sys.domain.dto.RoleDto;
import com.fullstackpeng.module.system.sys.domain.entity.SysRole;

@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysRoleMapstruct extends BaseMapstruct<RoleDto, SysRole> {
}
