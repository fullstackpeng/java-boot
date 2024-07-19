package com.fullstackpeng.module.system.basic.service.mapstruct;


import com.fullstackpeng.common.base.mapstruct.BaseMapstruct;
import com.fullstackpeng.module.system.basic.domain.dto.BasOrgDto;
import com.fullstackpeng.module.system.basic.domain.entity.BasOrg;

@org.mapstruct.Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface BasOrgMapstruct extends BaseMapstruct<BasOrgDto, BasOrg> {
}
