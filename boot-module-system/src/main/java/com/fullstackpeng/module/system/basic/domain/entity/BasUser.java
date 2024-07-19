package com.fullstackpeng.module.system.basic.domain.entity;

import com.fullstackpeng.common.data.jpa.core.domain.BaseEntity;
import com.fullstackpeng.common.data.jpa.core.identifier.IdGenerator;
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
@Table(name = "bas_user")
public class BasUser extends BaseEntity {
    @Id
    @IdGenerator
    private String id;
    /**
     * 所属机构
     */
    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "org_id")
    private BasOrg org;

    /**
     * 所属岗位
     */
    @ManyToMany(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinTable(name = "bas_user_post",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id"))
    private List<BasPost> posts;
    /**
     * 角色
     */
    @ManyToMany(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinTable(name = "bas_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<BasRole> roles;

    /**
     * 昵称
     */
    private String nickname;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名
     */
    private String username;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别,0 保密,1 男,2 女
     */
    private Integer gender = 0;
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
    /**
     * 密码重置时间
     */
    private Date pwdResetTime;
    /**
     * 是否系统用户
     */
    @Column(name = "is_system", columnDefinition = "bit(1) default 0")
    public Boolean system = false;

    /**
     * 状态
     */
    @Column(name = "is_enabled", columnDefinition = "bit(1) default 1")
    private Boolean enabled = true;

    /**
     * 商户识别码
     */
    private String sysCode;
}
