package com.fullstackpeng.module.system.sys.domain.vo;

import com.fullstackpeng.common.base.utils.TreeUtil;
import com.fullstackpeng.common.security.domain.vo.TreeVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class AreaTreeVo implements TreeUtil.Node<AreaTreeVo, String>, TreeVO<AreaTreeVo> {
    @Schema(description = "id")
    private String id;
    @Schema(description = "父类id")
    private String parentId;
    @Schema(description = "区域编码")
    private String code;
    @Schema(description = "区域名称")
    private String name;
    @Schema(description = "子类", implementation = AreaTreeVo.class, allOf = AreaTreeVo.class)
    private List<AreaTreeVo> children;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    @Override
    public List<AreaTreeVo> getChildren() {
        return children;
    }

    @Override
    public void setChildren(List<AreaTreeVo> children) {
        this.children = children;
    }
}
