package com.fullstackpeng.module.system.basic.controller;

import com.fullstackpeng.common.base.R;
import com.fullstackpeng.common.base.utils.TreeUtil;
import com.fullstackpeng.common.security.utils.SecurityUtil;
import com.fullstackpeng.module.system.basic.domain.dto.BasPermissionDto;
import com.fullstackpeng.module.system.basic.service.BasPermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bas/permission")
@Tag(name = "履约端：权限管理")
@RequiredArgsConstructor
public class BasPermissionController {
    private final BasPermissionService basPermissionService;

    /**
     * 菜单权限树
     *
     * @return 权限列表
     */
    @GetMapping("/tree")
    @Operation(summary = "菜单权限树")
    public R<List<BasPermissionDto>> tree() {
        String sysCode = SecurityUtil.getSysCode();
        List<BasPermissionDto> res = basPermissionService.list(sysCode);
        res = TreeUtil.buildTree(res);
        return R.OK(res);
    }
}
