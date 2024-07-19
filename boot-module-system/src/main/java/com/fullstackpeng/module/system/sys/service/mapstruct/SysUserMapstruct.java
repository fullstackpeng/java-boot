package com.fullstackpeng.module.system.sys.service.mapstruct;


import com.fullstackpeng.common.base.mapstruct.BaseMapstruct;
import com.fullstackpeng.module.system.sys.domain.dto.UserDto;
import com.fullstackpeng.module.system.sys.domain.dto.UserSaveDto;
import com.fullstackpeng.module.system.sys.domain.entity.SysUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysUserMapstruct extends BaseMapstruct<UserDto, SysUser> {

    /**
     * dto转entity
     *
     * @param dto dto
     * @return entity
     */
    SysUser saveDtoToEntity(UserSaveDto dto);
}
