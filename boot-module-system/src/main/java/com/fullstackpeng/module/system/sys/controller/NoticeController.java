package com.fullstackpeng.module.system.sys.controller;

import com.fullstackpeng.common.api.JsfPage;
import com.fullstackpeng.common.base.R;
import com.fullstackpeng.module.system.sys.domain.dto.NoticeDto;
import com.fullstackpeng.module.system.sys.domain.query.NoticeQuery;
import com.fullstackpeng.module.system.sys.service.SysNoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sys/notice")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "管理端：系统公告管理")
public class NoticeController {
    private final SysNoticeService sysNoticeService;

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    @GetMapping
    @Operation(summary = "分页查询")
    @PreAuthorize("hasAnyAuthority('sys:notice:query')")
    public R<JsfPage<NoticeDto>> page(NoticeQuery query) {
        JsfPage<NoticeDto> res = sysNoticeService.page(query);
        return R.OK(res);
    }

    /**
     * 保存
     *
     * @param dto dto
     * @return 是否成功
     */
    @PostMapping
    @Operation(summary = "新增公告")
    @PreAuthorize("hasAnyAuthority('sys:notice:save')")
    public R<String> save(@Valid @RequestBody NoticeDto dto) {
        sysNoticeService.save(dto);
        return R.OK();
    }

    /**
     * 更新公告
     *
     * @param dto .
     * @return .
     */
    @PutMapping
    @Operation(summary = "更新公告")
    @PreAuthorize("hasAnyAuthority('sys:notice:update')")
    public R<String> updateById(@Valid @RequestBody NoticeDto dto) {
        sysNoticeService.updateById(dto);
        return R.OK();
    }

    /**
     * 根据ID删除公告
     *
     * @param ids .
     * @return .
     */
    @DeleteMapping
    @Operation(summary = "删除公告")
    @PreAuthorize("hasAnyAuthority('sys:notice:delete')")
    public R<String> deleteByIds(@RequestBody List<String> ids) {
        sysNoticeService.deleteByIds(ids);
        return R.OK();
    }
}
