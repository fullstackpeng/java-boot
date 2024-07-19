package com.fullstackpeng.module.system.basic.service.mapstruct;

import com.fullstackpeng.common.base.mapstruct.BaseMapstruct;
import com.fullstackpeng.module.system.basic.domain.dto.BasPermissionDto;
import com.fullstackpeng.module.system.basic.domain.entity.BasPermission;
import com.fullstackpeng.module.system.sys.domain.dto.PermissionDto;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface BasPermissionMapstruct extends BaseMapstruct<BasPermissionDto, BasPermission> {
    /**
     * 转换
     *
     * @param permissions .
     * @return .
     */
    List<PermissionDto> toPermissionDtoList(List<BasPermission> permissions);
}
