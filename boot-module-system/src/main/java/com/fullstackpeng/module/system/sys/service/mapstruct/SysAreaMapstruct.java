package com.fullstackpeng.module.system.sys.service.mapstruct;


import com.fullstackpeng.common.base.mapstruct.BaseMapstruct;
import com.fullstackpeng.module.system.sys.domain.dto.AreaDto;
import com.fullstackpeng.module.system.sys.domain.entity.SysArea;
import com.fullstackpeng.module.system.sys.domain.vo.AreaTreeVo;

import java.util.List;


@org.mapstruct.Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysAreaMapstruct extends BaseMapstruct<AreaDto, SysArea> {

    /**
     * 转换
     *
     * @param list .
     * @return .
     */
    List<AreaTreeVo> toTreeVoList(List<SysArea> list);
}
