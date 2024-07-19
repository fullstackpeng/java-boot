package com.fullstackpeng.module.system.common.controller.mapstruct;


import com.fullstackpeng.module.system.basic.domain.entity.BasUser;
import com.fullstackpeng.module.system.common.domain.vo.UserVo;
import com.fullstackpeng.module.system.sys.domain.entity.SysUser;

@org.mapstruct.Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface UserMapstruct {

    /**
     * 转换
     *
     * @param entity 实体
     * @return vo
     */
    UserVo toVo(SysUser entity);

    /**
     * 转换
     *
     * @param entity 实体
     * @return vo
     */
    UserVo toVo(BasUser entity);
}
