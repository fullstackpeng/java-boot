package com.fullstackpeng.module.system.sys.service.mapstruct;


import com.fullstackpeng.common.base.mapstruct.BaseMapstruct;
import com.fullstackpeng.module.system.sys.domain.dto.TenantSmallDto;
import com.fullstackpeng.module.system.sys.domain.entity.SysTenant;

@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysTenantSmallMapstruct extends BaseMapstruct<TenantSmallDto, SysTenant> {
}
