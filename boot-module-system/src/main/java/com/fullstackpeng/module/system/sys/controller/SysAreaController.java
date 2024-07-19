package com.fullstackpeng.module.system.sys.controller;

import com.fullstackpeng.common.api.JsfPage;
import com.fullstackpeng.common.base.R;
import com.fullstackpeng.module.system.sys.domain.dto.AreaDto;
import com.fullstackpeng.module.system.sys.domain.query.AreaQuery;
import com.fullstackpeng.module.system.sys.domain.vo.AreaTreeVo;
import com.fullstackpeng.module.system.sys.service.SysAreaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 地区
 */
@RestController
@RequestMapping("/sys/area")
@RequiredArgsConstructor
public class SysAreaController {
    private final SysAreaService areaService;

    /**
     * 树,查询已启用的
     *
     * @return .
     */
    @GetMapping("/tree")
    @Operation(summary = "树形结构地区", description = "查询已启用的")
    public R<List<AreaTreeVo>> treeEnabled() {
        return R.OK(areaService.treeEnabled());
    }

    /**
     * 分页
     *
     * @param query .
     * @return .
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询")
    @PreAuthorize("hasAnyAuthority('sys:area:query')")
    public R<JsfPage<AreaDto>> page(AreaQuery query) {
        return R.OK(areaService.page(query));
    }

    /**
     * 查询
     *
     * @param query .
     * @return .
     */
    @GetMapping("/list")
    @Operation(summary = "查询")
    @PreAuthorize("hasAnyAuthority('sys:area:query')")
    public R<List<AreaDto>> list(AreaQuery query) {
        return R.OK(areaService.list(query));
    }

    /**
     * 保存
     *
     * @param dto .
     * @return .
     */
    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAnyAuthority('sys:area:save')")
    public R<String> save(@Valid @RequestBody AreaDto dto) {
        areaService.save(dto);
        return R.OK();
    }

    /**
     * 更新
     *
     * @param dto .
     * @return .
     */
    @PutMapping
    @Operation(summary = "更新")
    @PreAuthorize("hasAnyAuthority('sys:area:update')")
    public R<String> update(@Valid @RequestBody AreaDto dto) {
        areaService.updateById(dto);
        return R.OK();
    }


    /**
     * 删除
     *
     * @param id 主键
     * @return .
     */
    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAnyAuthority('sys:area:delete')")
    public R<String> delete(String id) {
        areaService.deleteById(id);
        return R.OK();
    }
}
