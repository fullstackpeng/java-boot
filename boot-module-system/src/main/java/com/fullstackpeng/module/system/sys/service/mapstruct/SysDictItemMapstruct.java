package com.fullstackpeng.module.system.sys.service.mapstruct;


import com.fullstackpeng.common.base.mapstruct.BaseMapstruct;
import com.fullstackpeng.module.system.common.domain.vo.SelectOptionVo;
import com.fullstackpeng.module.system.sys.domain.dto.DictItemDto;
import com.fullstackpeng.module.system.sys.domain.entity.SysDictItem;

import java.util.List;


@org.mapstruct.Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface SysDictItemMapstruct extends BaseMapstruct<DictItemDto, SysDictItem> {


    /**
     * 转换为SelectOption
     *
     * @param dtoList .
     * @return .
     */
    List<SelectOptionVo> toSelectOption(List<DictItemDto> dtoList);
}
