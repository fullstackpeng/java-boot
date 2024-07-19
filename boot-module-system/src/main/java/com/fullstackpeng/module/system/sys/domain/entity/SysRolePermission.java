package com.fullstackpeng.module.system.sys.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@Entity
@Table(name = "sys_role_permission")
public class SysRolePermission implements Serializable {
    /**
     * 角色id
     */
    @Id
    @Column(name = "role_id")
    private String roleId;
    /**
     * 权限id
     */
    @Id
    @Column(name = "permission_id")
    private String permissionId;
}
