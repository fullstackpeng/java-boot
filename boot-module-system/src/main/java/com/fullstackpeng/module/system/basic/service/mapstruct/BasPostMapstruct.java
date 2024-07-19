package com.fullstackpeng.module.system.basic.service.mapstruct;


import com.fullstackpeng.common.base.mapstruct.BaseMapstruct;
import com.fullstackpeng.module.system.basic.domain.dto.BasPostDto;
import com.fullstackpeng.module.system.basic.domain.entity.BasPost;

@org.mapstruct.Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface BasPostMapstruct extends BaseMapstruct<BasPostDto, BasPost> {
}
