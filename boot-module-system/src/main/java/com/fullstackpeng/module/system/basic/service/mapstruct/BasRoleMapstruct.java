package com.fullstackpeng.module.system.basic.service.mapstruct;

import com.fullstackpeng.common.base.mapstruct.BaseMapstruct;
import com.fullstackpeng.module.system.basic.domain.dto.BasRoleDto;
import com.fullstackpeng.module.system.basic.domain.entity.BasRole;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BasRoleMapstruct extends BaseMapstruct<BasRoleDto, BasRole> {
}
