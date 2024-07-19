package com.fullstackpeng.module.system.basic.controller;

import com.fullstackpeng.common.base.R;
import com.fullstackpeng.common.base.utils.StrUtil;
import com.fullstackpeng.common.base.utils.TreeUtil;
import com.fullstackpeng.common.security.utils.SecurityUtil;
import com.fullstackpeng.module.system.basic.domain.dto.BasOrgDto;
import com.fullstackpeng.module.system.basic.domain.query.BasOrgQuery;
import com.fullstackpeng.module.system.basic.domain.query.BasOrgTreeQuery;
import com.fullstackpeng.module.system.basic.service.BasOrgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bas/org")
@RequiredArgsConstructor
@Tag(name = "履约端：机构管理")
public class BasOrgController {
    private final BasOrgService basOrgService;

    /**
     * 机构树
     *
     * @return .
     */
    @GetMapping("/tree")
    @Operation(summary = "机构树")
    public R<List<BasOrgDto>> tree(BasOrgTreeQuery query) {
        String sysCode = SecurityUtil.getSysCode();
        query.setSysCode(sysCode);
        List<BasOrgDto> res = basOrgService.list(query);
        res = TreeUtil.buildTree(res);
        return R.OK(res);
    }

    /**
     * 机构信息
     *
     * @param query .
     * @return .
     */
    @GetMapping
    @Operation(summary = "机构列表查询")
    @PreAuthorize("hasAuthority('bas:org:query')")
    public R<List<BasOrgDto>> list(BasOrgQuery query) {
        String sysCode = SecurityUtil.getSysCode();
        query.setSysCode(sysCode);
        List<BasOrgDto> res = basOrgService.list(query);
        return R.OK(res);
    }


    /**
     * 新增机构
     *
     * @param dto .
     * @return .
     */
    @PostMapping
    @Operation(summary = "新增机构")
    @PreAuthorize("hasAuthority('bas:org:save')")
    public R<String> save(@Valid @RequestBody BasOrgDto dto) {
        String parentId = dto.getParentId();
        if (StrUtil.isBlank(parentId)) {
            return R.NG("父类ID不能为空");
        }
        String sysCode = SecurityUtil.getSysCode();
        dto.setSysCode(sysCode);
        basOrgService.save(dto);
        return R.OK();
    }

    /**
     * 修改机构
     *
     * @param dto .
     * @return .
     */
    @PutMapping
    @Operation(summary = "修改机构")
    @PreAuthorize("hasAuthority('bas:org:update')")
    public R<String> update(@Valid @RequestBody BasOrgDto dto) {
        String id = dto.getId();
        if (id == null) {
            return R.NG("id不能为空");
        }
        String sysCode = SecurityUtil.getSysCode();
        dto.setSysCode(sysCode);
        basOrgService.updateById(dto);
        return R.OK();
    }

    /**
     * 删除机构
     *
     * @param id .
     * @return .
     */
    @DeleteMapping
    @Operation(summary = "删除机构")
    @PreAuthorize("hasAuthority('bas:org:delete')")
    public R<String> delete(String id) {
        basOrgService.deleteById(id);
        return R.OK();
    }
}
