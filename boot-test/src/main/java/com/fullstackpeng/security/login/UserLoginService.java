package com.fullstackpeng.security.login;

import com.fullstackpeng.common.base.utils.StrUtil;
import com.fullstackpeng.common.security.domain.dto.TenantInfoDto;
import com.fullstackpeng.common.security.domain.dto.UserInfoDto;
import com.fullstackpeng.common.security.domain.dto.UserOrgInfoDto;
import com.fullstackpeng.module.system.basic.domain.entity.BasOrg;
import com.fullstackpeng.module.system.basic.domain.entity.BasPermission;
import com.fullstackpeng.module.system.basic.domain.entity.BasRole;
import com.fullstackpeng.module.system.basic.domain.entity.BasUser;
import com.fullstackpeng.module.system.basic.service.*;
import com.fullstackpeng.module.system.sys.domain.entity.SysPermission;
import com.fullstackpeng.module.system.sys.domain.entity.SysRole;
import com.fullstackpeng.module.system.sys.domain.entity.SysUser;
import com.fullstackpeng.module.system.sys.service.SysPermissionService;
import com.fullstackpeng.module.system.sys.service.SysRoleService;
import com.fullstackpeng.module.system.sys.service.SysUserService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserLoginService implements com.fullstackpeng.common.security.service.UserLoginService  {
    private final SysUserService sysUserService;
    private final SysRoleService sysRoleService;
    private final SysPermissionService sysPermissionService;


    private final BasUserService basUserService;
    private final BasRoleService basRoleService;
    private final BasPermissionService basPermissionService;
    private final BasOrgService basOrgService;
    private final BasRoleDataService basRoleDataService;

    @Override
    public Optional<String> getSysCodeByUsername(String username) {
        return Optional.ofNullable(basUserService.getSysCodeByUsername(username));
    }

    @Override
    public UserInfoDto findByUsername(String username) {
        return tenantLogin(username);
    }

    @Override
    public UserInfoDto findByUsername(String username, @Nullable String sysCode) {
        return bizLogin(username);
    }

    /**
     * 商户端登录
     *
     * @param username .
     * @return .
     */
    private UserInfoDto tenantLogin(String username) {
        SysUser user = sysUserService.findByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (!Boolean.TRUE.equals(user.getEnabled())) {
            throw new UsernameNotFoundException("用户已被禁用");
        }
        // 角色
        List<SysRole> roles = sysRoleService.listByUserId(user.getId());
        // 权限
        List<SysPermission> permissions = sysPermissionService.listByUserId(user.getId());

        UserInfoDto userInfo = new UserInfoDto();

        // id
        userInfo.setId(user.getId());
        // 用户名
        userInfo.setUsername(user.getUsername());
        // 密码
        userInfo.setPassword(user.getPassword());
        // 昵称
        userInfo.setNickname(user.getNickname());
        // 性别
//        userInfo.setGender(user.getGender());
        // 邮箱
        userInfo.setEmail(user.getEmail());
        // 电话
        userInfo.setPhone(user.getPhone());
        // 头像
        userInfo.setAvatar(user.getAvatar());
        // 角色
        userInfo.setRoles(getRolesByAdmin(roles));
        // 权限
        userInfo.setPermissions(getPermissionsByAdmin(permissions));
        // 是否启用
        userInfo.setEnabled(user.getEnabled());
        // 是否系统用户
        userInfo.setSystem(user.getSystem());
        // 系统编码
        userInfo.setSysCode(null);
        // 商户
        TenantInfoDto tenantInfo = new TenantInfoDto();
        tenantInfo.setName("PureAdmin");
        tenantInfo.setLogo(null);
        userInfo.setTenantInfo(tenantInfo);
        return userInfo;
    }

    /**
     * 履约端登录
     *
     * @param username .
     * @return .
     */
    public UserInfoDto bizLogin(String username) {
        BasUser user = basUserService.findByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (!Boolean.TRUE.equals(user.getEnabled())) {
            throw new UsernameNotFoundException("用户已被禁用");
        }
        BasOrg orgInfo = basOrgService.getById(user.getOrg().getId());
        if (null == orgInfo) {
            throw new UsernameNotFoundException("用户未分配机构，无法登录");
        }

        Set<String> dataScope = basRoleDataService.getRoleDataScope(user.getId(), orgInfo.getId());

        BasOrg tenantInfo = basOrgService.getTopOrg(orgInfo.getSysCode());

        //商户有效期
        basOrgService.checkOrgExpiredForLogin(orgInfo.getId());
        // 角色
        List<BasRole> roles = basRoleService.findByUserId(user.getId());
        // 权限
        List<BasPermission> permissions = basPermissionService.listByUserId(user.getId());
        UserInfoDto userInfo = new UserInfoDto();
        // id
        userInfo.setId(user.getId());
        // 用户名
        userInfo.setUsername(user.getUsername());
        // 密码
        userInfo.setPassword(user.getPassword());
        // 昵称
        userInfo.setNickname(user.getNickname());
        // 头像
        userInfo.setAvatar(user.getAvatar());
        // 邮箱
        userInfo.setEmail(user.getEmail());
        // 电话
        userInfo.setPhone(user.getPhone());
        // 系统编码
        userInfo.setSysCode(user.getSysCode());
        // 机构
        UserOrgInfoDto userOrgInfoDto = new UserOrgInfoDto();
        userOrgInfoDto.setId(orgInfo.getId());
        userOrgInfoDto.setName(orgInfo.getName());
        userOrgInfoDto.setPath(orgInfo.getPath());
        userInfo.setOrgInfo(userOrgInfoDto);
        // 商户信息
        TenantInfoDto tenantInfoDto = new TenantInfoDto();
        tenantInfoDto.setName(tenantInfo.getName());
        tenantInfoDto.setLogo(tenantInfo.getLogo());
        userInfo.setTenantInfo(tenantInfoDto);
        // 角色
        userInfo.setRoles(getRoles(roles));
        // 权限
        userInfo.setPermissions(getPermissions(permissions));
        // 是否启用
        userInfo.setEnabled(user.getEnabled());
        // 是否系统用户
        userInfo.setSystem(user.getSystem());
        // 数据范围
        userInfo.setDataScopes(new ArrayList<>(dataScope));
        return userInfo;
    }

    private List<String> getPermissionsByAdmin(List<SysPermission> permissions) {
        if (permissions == null || permissions.isEmpty()) {
            return null;
        }
        return permissions.stream()
                .filter(e -> Boolean.TRUE.equals(e.getEnabled()))
                .map(SysPermission::getPermission)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());
    }

    private List<String> getRolesByAdmin(List<SysRole> roles) {
        if (roles == null || roles.isEmpty()) {
            return null;
        }
        return roles.stream()
                .filter(e -> Boolean.TRUE.equals(e.getEnabled()))
                .map(SysRole::getCode)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());
    }

    private List<String> getPermissions(List<BasPermission> permissions) {
        if (permissions == null || permissions.isEmpty()) {
            return null;
        }
        return permissions.stream()
                .filter(e -> Boolean.TRUE.equals(e.getEnabled()))
                .map(BasPermission::getPermission)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());
    }

    private List<String> getRoles(List<BasRole> roles) {
        if (roles == null || roles.isEmpty()) {
            return null;
        }
        return roles.stream()
                .filter(e -> Boolean.TRUE.equals(e.getEnabled()))
                .map(BasRole::getCode)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());
    }
}
