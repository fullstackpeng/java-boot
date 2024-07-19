package com.fullstackpeng.module.system.sys.service.mapstruct;


import com.fullstackpeng.common.base.mapstruct.BaseMapstruct;
import com.fullstackpeng.module.system.sys.domain.dto.QuartzJobDto;
import com.fullstackpeng.module.system.sys.domain.entity.SysQuartzJob;

@org.mapstruct.Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysQuartzJobMapstruct extends BaseMapstruct<QuartzJobDto, SysQuartzJob> {
}
