package com.fullstackpeng.module.system.sys.service.mapstruct;


import com.fullstackpeng.common.base.mapstruct.BaseMapstruct;
import com.fullstackpeng.module.system.sys.domain.dto.DictDto;
import com.fullstackpeng.module.system.sys.domain.entity.SysDict;

@org.mapstruct.Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysDictMapstruct extends BaseMapstruct<DictDto, SysDict> {
}
