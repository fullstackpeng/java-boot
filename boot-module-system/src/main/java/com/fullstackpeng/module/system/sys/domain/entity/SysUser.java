package com.fullstackpeng.module.system.sys.domain.entity;

import com.fullstackpeng.common.data.jpa.core.domain.BaseEntity;
import com.fullstackpeng.common.data.jpa.core.identifier.IdGenerator;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "sys_user")
public class SysUser extends BaseEntity {
    /**
     * 用户id
     */
    @Id
    @IdGenerator
    private String id;
    /**
     * 角色
     */
    @ManyToMany(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinTable(name = "sys_user_role",
            joinColumns = @jakarta.persistence.JoinColumn(name = "user_id"),
            inverseJoinColumns = @jakarta.persistence.JoinColumn(name = "role_id"))
    private List<SysRole> roles;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
    /**
     * 性别 0 未知 1 男 2 女
     */
    private Integer gender = 0;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
    /**
     * 最后登录ip
     */
    private String lastLoginIp;
    /**
     * 最后修改密码的时间
     */
    private Date lastPwdResetTime;
    /**
     * 描述
     */
    private String description;
    /**
     * 是否系统用户
     */
    @Column(name = "`is_system`", columnDefinition = "tinyint(1) default 0")
    private Boolean system = false;

    /**
     * 状态
     */
    @Column(name = "`is_enabled`", columnDefinition = "tinyint(1) default 1")
    private Boolean enabled = true;

}
