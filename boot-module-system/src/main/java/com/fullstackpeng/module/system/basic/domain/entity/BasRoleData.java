package com.fullstackpeng.module.system.basic.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "bas_role_data")
public class BasRoleData implements Serializable {
    /**
     * id
     */
    @Id
    @Column(name = "role_id")
    private String roleId;
    /**
     * 机构id
     */
    @Id
    @Column(name = "org_id")
    private String orgId;
}
