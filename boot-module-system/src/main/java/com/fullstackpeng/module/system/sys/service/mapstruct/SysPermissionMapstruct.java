package com.fullstackpeng.module.system.sys.service.mapstruct;

import com.fullstackpeng.common.base.mapstruct.BaseMapstruct;
import com.fullstackpeng.module.system.sys.domain.dto.PermissionDto;
import com.fullstackpeng.module.system.sys.domain.entity.SysPermission;
import com.fullstackpeng.module.system.sys.domain.entity.SysTenantPermission;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysPermissionMapstruct extends BaseMapstruct<PermissionDto, SysPermission> {

    List<PermissionDto> tenantToDtoList(List<SysTenantPermission> permissions);
}
