package com.fullstackpeng.module.system.basic.service.mapstruct;

import com.fullstackpeng.common.base.mapstruct.BaseMapstruct;
import com.fullstackpeng.module.system.basic.domain.dto.BasNoticeDto;
import com.fullstackpeng.module.system.sys.domain.entity.SysNotice;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BasNoticeMapstruct extends BaseMapstruct<BasNoticeDto, SysNotice> {
}
