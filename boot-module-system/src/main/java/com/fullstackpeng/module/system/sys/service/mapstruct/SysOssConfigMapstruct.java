package com.fullstackpeng.module.system.sys.service.mapstruct;

import com.fullstackpeng.common.base.mapstruct.BaseMapstruct;
import com.fullstackpeng.module.system.sys.domain.dto.OssConfigDto;
import com.fullstackpeng.module.system.sys.domain.entity.SysOssConfig;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysOssConfigMapstruct extends BaseMapstruct<OssConfigDto, SysOssConfig> {
}
