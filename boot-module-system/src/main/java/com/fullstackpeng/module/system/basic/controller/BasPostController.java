package com.fullstackpeng.module.system.basic.controller;

import com.fullstackpeng.common.api.JsfPage;
import com.fullstackpeng.common.base.R;
import com.fullstackpeng.common.base.utils.StrUtil;
import com.fullstackpeng.common.security.utils.SecurityUtil;
import com.fullstackpeng.module.system.basic.domain.dto.BasPostDto;
import com.fullstackpeng.module.system.basic.domain.query.BasPostQuery;
import com.fullstackpeng.module.system.basic.service.BasPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bas/post")
@Tag(name = "履约端：岗位管理")
@RequiredArgsConstructor
public class BasPostController {
    private final BasPostService basPostService;

    /**
     * 岗位编码是否存在
     *
     * @param code 岗位编码
     * @param id   id
     * @return 是否存在
     */
    @GetMapping("/exists/code")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "code", description = "岗位编码", required = true),
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "id", required = false)
    })
    @Operation(summary = "岗位编码是否存在")
    public R<Boolean> exists(@RequestParam String code,
                             @RequestParam(required = false) String id) {
        String sysCode = SecurityUtil.getSysCode();
        return R.OK(basPostService.existsByCodeAndSysCode(code, sysCode, id));
    }

    /**
     * 分页
     *
     * @param query 查询条件
     * @return 分页
     */
    @GetMapping("/page")
    @Operation(summary = "岗位分页")
    @PreAuthorize("hasAnyAuthority('bas:post:page')")
    public R<JsfPage<BasPostDto>> page(BasPostQuery query) {
        return R.OK(basPostService.page(query));
    }

    /**
     * 列表
     *
     * @param query 查询条件
     * @return 列表
     */
    @GetMapping("/list")
    @Operation(summary = "岗位列表")
    public R<List<BasPostDto>> list(BasPostQuery query) {
        return R.OK(basPostService.list(query));
    }

    /**
     * 保存
     *
     * @param dto 岗位信息
     * @return 是否成功
     */
    @Operation(summary = "保存岗位")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('bas:post:save')")
    public R<String> save(@Valid @RequestBody BasPostDto dto) {
        basPostService.save(dto);
        return R.OK();
    }

    /**
     * 更新
     *
     * @param dto 岗位信息
     * @return 是否成功
     */
    @Operation(summary = "更新岗位")
    @PutMapping
    @PreAuthorize("hasAnyAuthority('bas:post:update')")
    public R<String> update(@Valid @RequestBody BasPostDto dto) {
        String id = dto.getId();
        if (StrUtil.isBlank(id)) {
            return R.NG("id不能为空");
        }
        basPostService.updateById(dto);
        return R.OK();
    }

    /**
     * 删除
     *
     * @param id id
     * @return 是否成功
     */
    @Operation(summary = "删除岗位")
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('bas:post:delete')")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "id", required = true)
    })
    public R<String> delete(@RequestParam String id) {
        basPostService.deleteById(id);
        return R.OK();
    }

}
