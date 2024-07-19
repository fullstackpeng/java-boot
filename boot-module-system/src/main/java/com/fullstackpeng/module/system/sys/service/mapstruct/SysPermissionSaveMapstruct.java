package com.fullstackpeng.module.system.sys.service.mapstruct;


import com.fullstackpeng.common.base.mapstruct.BaseMapstruct;
import com.fullstackpeng.module.system.sys.domain.dto.PermissionSaveDto;
import com.fullstackpeng.module.system.sys.domain.entity.SysPermission;
import com.fullstackpeng.module.system.sys.domain.entity.SysTenantPermission;

@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysPermissionSaveMapstruct extends BaseMapstruct<PermissionSaveDto, SysPermission> {

    /**
     * 转换
     *
     * @param dto .
     * @return .
     */
    SysTenantPermission toTenant(PermissionSaveDto dto);
}
