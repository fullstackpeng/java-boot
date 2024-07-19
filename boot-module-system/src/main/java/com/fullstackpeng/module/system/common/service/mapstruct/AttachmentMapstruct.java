package com.fullstackpeng.module.system.common.service.mapstruct;


import com.fullstackpeng.common.base.mapstruct.BaseMapstruct;
import com.fullstackpeng.module.system.common.domain.dto.AttachmentDto;
import com.fullstackpeng.module.system.common.domain.entity.Attachment;

@org.mapstruct.Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface AttachmentMapstruct extends BaseMapstruct<AttachmentDto, Attachment> {
}
