package com.fullstackpeng.common.security.controller.mapstruct;

import com.fullstackpeng.common.base.mapstruct.BaseMapstruct;
import com.fullstackpeng.common.security.domain.dto.UserInfoDto;
import com.fullstackpeng.common.security.domain.vo.LoginInfoVo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface LoginInfoMapstruct extends BaseMapstruct<LoginInfoVo, UserInfoDto> {

}
