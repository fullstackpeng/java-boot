package com.fullstackpeng.module.system.basic.service.mapstruct;


import com.fullstackpeng.common.base.mapstruct.BaseMapstruct;
import com.fullstackpeng.module.system.basic.domain.dto.BasUserDto;
import com.fullstackpeng.module.system.basic.domain.dto.BasUserSaveDto;
import com.fullstackpeng.module.system.basic.domain.entity.BasUser;

@org.mapstruct.Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface BasUserMapstruct extends BaseMapstruct<BasUserDto, BasUser> {

    BasUser toEntity(BasUserSaveDto dto);
}
