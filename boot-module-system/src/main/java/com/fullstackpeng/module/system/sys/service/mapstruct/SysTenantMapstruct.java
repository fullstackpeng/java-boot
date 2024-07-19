package com.fullstackpeng.module.system.sys.service.mapstruct;


import com.fullstackpeng.common.base.mapstruct.BaseMapstruct;
import com.fullstackpeng.module.system.sys.domain.dto.TenantDto;
import com.fullstackpeng.module.system.sys.domain.entity.SysTenant;

@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysTenantMapstruct extends BaseMapstruct<TenantDto, SysTenant> {
}
