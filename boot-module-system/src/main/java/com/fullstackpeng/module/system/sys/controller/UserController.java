package com.fullstackpeng.module.system.sys.controller;

import com.fullstackpeng.common.api.JsfPage;
import com.fullstackpeng.common.base.R;
import com.fullstackpeng.common.base.utils.CollectionUtil;
import com.fullstackpeng.common.base.utils.PasswordUtil;
import com.fullstackpeng.common.security.utils.SecurityUtil;
import com.fullstackpeng.module.system.sys.domain.dto.UserDto;
import com.fullstackpeng.module.system.sys.domain.dto.UserRestPwdDto;
import com.fullstackpeng.module.system.sys.domain.dto.UserSaveDto;
import com.fullstackpeng.module.system.sys.domain.query.UserQuery;
import com.fullstackpeng.module.system.sys.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sys/user")
@RequiredArgsConstructor
@Tag(name = "管理端：用户管理")
public class UserController {
    private final SysUserService sysUserService;

    /**
     * 分页查询用户
     *
     * @param query 查询条件
     * @return 用户分页数据
     */
    @GetMapping
    @Operation(summary = "分页查询用户")
    @PreAuthorize("hasAnyAuthority('sys:user:list')")
    public R<JsfPage<UserDto>> page(UserQuery query) {
        JsfPage<UserDto> res = sysUserService.page(query);
        // 忽略密码
        List<UserDto> records = res.getRecords();
        if (CollectionUtil.isNotEmpty(records)) {
            records.forEach(userDto -> userDto.setPassword(null));
        }
        return R.OK(res);
    }

    /**
     * 查询用户列表
     *
     * @param query 查询条件
     * @return 用户列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询用户列表")
    public R<List<UserDto>> list(UserQuery query) {
        List<UserDto> res = sysUserService.list(query);
        // 忽略密码
        res.forEach(userDto -> userDto.setPassword(null));
        return R.OK(res);
    }

    /**
     * 用户名是否存在
     *
     * @param username .
     * @param id       .
     * @return .
     */
    @GetMapping("/existsByUsername")
    @Operation(summary = "用户名是否存在")
    @Parameters({
            @Parameter(name = "username", description = "用户名", required = true, example = "admin"),
            @Parameter(name = "id", description = "需要排除的用户ID", required = false, example = "1")
    })
    public R<Boolean> existsByUsernameAndIdNot(@RequestParam("username") String username,
                                               @RequestParam(name = "id", required = false) String id) {
        Boolean res = sysUserService.existsByUsername(username, id);
        return R.OK(res);
    }

    /**
     * 保存用户
     *
     * @param dto 用户信息
     * @return 是否成功
     */
    @PostMapping
    @Operation(summary = "保存用户")
    @PreAuthorize("hasAnyAuthority('sys:user:save')")
    public R<String> save(@Valid @RequestBody UserSaveDto dto) {
        dto.setPassword(PasswordUtil.encoder(dto.getPassword()));
        sysUserService.save(dto);
        return R.OK();
    }

    /**
     * 更新用户
     *
     * @param dto 用户信息
     * @return 是否成功
     */
    @PutMapping
    @Operation(summary = "更新用户")
    @PreAuthorize("hasAnyAuthority('sys:user:update')")
    public R<String> update(@Valid @RequestBody UserSaveDto dto) {
        dto.setPassword(null);
        sysUserService.updateById(dto);
        return R.OK();
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 是否成功
     */
    @DeleteMapping
    @Operation(summary = "删除用户")
    @PreAuthorize("hasAnyAuthority('sys:user:delete')")
    @Parameters({
            @Parameter(name = "id", description = "用户ID", required = true, example = "1")
    })
    public R<String> delete(@RequestParam("id") String id) {
        sysUserService.deleteById(id);
        return R.OK();
    }

    /**
     * 重置密码
     *
     * @param dto 数据
     * @return 是否成功
     */
    @PutMapping("/resetPassword")
    @Operation(summary = "重置密码")
    @PreAuthorize("hasAnyAuthority('sys:user:resetPassword')")
    public R<String> resetPassword(@Valid @RequestBody UserRestPwdDto dto) {
        String password = dto.getPassword();
        String pwd = PasswordUtil.encoder(password);
        dto.setPassword(pwd);
        String username = SecurityUtil.getUsername();
        dto.setOperator(username);
        sysUserService.resetPassword(dto);
        return R.OK();
    }
}
