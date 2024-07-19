package com.fullstackpeng.common.security.domain.dto;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
public class UserInfoDto implements UserDetails {
    /**
     * 用户ID
     */
    private String id;
    /**
     * 用户
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 租户编码
     */
    private String sysCode;
    /**
     * 机构信息
     */
    private UserOrgInfoDto orgInfo;
    /**
     * 租户信息
     */
    private TenantInfoDto tenantInfo;
    /**
     * 用户角色
     */
    private List<String> roles;
    /**
     * 用户权限
     */
    private List<String> permissions;
    /**
     * 数据权限
     */
    private List<String> dataScopes;
    /**
     * 是否启用
     */
    private Boolean enabled;
    /**
     * 是否系统用户
     */
    private Boolean system;

    @Override
    public Collection<AuthorityDto> getAuthorities() {
        List<AuthorityDto> authorities = new ArrayList<>();
        //角色+权限
        if (CollectionUtil.isNotEmpty(this.permissions)) {
            authorities.addAll(this.permissions.stream().map(AuthorityDto::new).toList());
        }
        if (CollectionUtil.isNotEmpty(this.roles)) {
            authorities.addAll(
                    this.roles.stream().map(AuthorityDto::new).toList()
            );
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(this.enabled);
    }
}
