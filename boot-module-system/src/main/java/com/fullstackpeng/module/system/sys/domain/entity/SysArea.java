package com.fullstackpeng.module.system.sys.domain.entity;

import com.fullstackpeng.common.data.jpa.core.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "t_area")
public class SysArea extends BaseEntity {
    @Id
    private String id;
    /**
     * 父级id
     */
    private String parentId;
    @NotBlank(message = "地区编码不能为空")
    private String code;
    @NotBlank(message = "地区名称不能为空")
    private String name;
    /**
     * 全称
     */
    private String fullName;
    /**
     * 树路径
     */
    private String path;
    /**
     * 级别
     */
    private Integer level;

    /**
     * 是否启用
     */
    @Column(name = "`is_enabled`", columnDefinition = "tinyint(1) default 1")
    private Boolean enabled;
}
