package com.fullstackpeng.module.system.basic.domain.entity;

import com.fullstackpeng.common.data.jpa.core.domain.BaseTenantEntity;
import com.fullstackpeng.common.data.jpa.core.identifier.IdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 岗位
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "bas_post")
public class BasPost extends BaseTenantEntity {

    @Id
    @IdGenerator
    private String id;
    /**
     * 岗位编码
     */
    private String code;
    /**
     * 岗位名称
     */
    private String name;
    /**
     * 岗位描述
     */
    private String description;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态
     */
    @Column(name = "is_enabled", columnDefinition = "tinyint(1) default 1")
    private Boolean enabled;

}
