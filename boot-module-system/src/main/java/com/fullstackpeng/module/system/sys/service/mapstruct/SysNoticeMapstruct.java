package com.fullstackpeng.module.system.sys.service.mapstruct;


import com.fullstackpeng.common.base.mapstruct.BaseMapstruct;
import com.fullstackpeng.module.system.sys.domain.dto.NoticeDto;
import com.fullstackpeng.module.system.sys.domain.entity.SysNotice;

@org.mapstruct.Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysNoticeMapstruct extends BaseMapstruct<NoticeDto, SysNotice> {
}
