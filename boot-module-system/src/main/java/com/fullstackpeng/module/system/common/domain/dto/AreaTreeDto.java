package com.fullstackpeng.module.system.common.domain.dto;

import com.fullstackpeng.common.base.utils.TreeUtil;
import com.fullstackpeng.module.system.sys.domain.dto.AreaDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class AreaTreeDto extends AreaDto implements TreeUtil.Node<AreaTreeDto, String> {
    private List<AreaTreeDto> children;

    @Override
    public List<AreaTreeDto> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<AreaTreeDto> children) {
        this.children = children;
    }
}
