package com.fullstackpeng.module.system.sys.domain.entity;

import com.fullstackpeng.common.data.jpa.core.domain.BaseEntity;
import com.fullstackpeng.common.data.jpa.core.identifier.IdGenerator;
import com.fullstackpeng.module.system.basic.domain.entity.BasUser;
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
@Table(name = "bas_organization")
public class SysTenant extends BaseEntity {

    @Id
    @IdGenerator
    private String id;
    /**
     * 租户字段
     */
    @NotBlank(message = "租户字段不能为空")
    private String sysCode;
    /**
     * 用户
     */
    @OneToMany(mappedBy = "org")
    private List<BasUser> users;
    /**
     * 机构名称
     */
    @NotBlank(message = "机构名称不能为空")
    private String name;
    /**
     * logo
     */
    private String logo;
    /**
     * 父机构ID
     */
    private String parentId;
    /**
     * 联系人
     */
    private String linkMan;
    /**
     * 联系电话
     */
    private String linkTel;
    /**
     * 联系邮箱
     */
    private String linkEmail;
    /**
     * 机构地址
     */
    private String address;
    /**
     * 总部标识
     */
    @Column(name = "`is_system`", columnDefinition = "tinyint(1) default 0")
    public Boolean system = true;

    /**
     * 是否启用sass
     */
    @Column(name = "`is_saas`", columnDefinition = "tinyint(1)` default 0")
    private Boolean saas;

    /**
     * 产品
     */
//    private String productId;
    @OneToOne
    @JoinColumn(name = "product_id")
    private SysProduct product;

    /**
     * 使用截止日期
     */
    private Date usedEndTime;

    /**
     * 网点等级 1：一级，2：二级，3：三级
     */
    private Integer level = 1;

    /**
     * 机构路径
     */
    private String path = id;
    /**
     * 机构类型
     * 1 厂商
     * 2 机构网点
     */
    private Integer type = 1;
    /**
     * 备注
     */
    private String memo;

    /**
     * 是否启用
     */
    @Column(name = "`is_enabled`", columnDefinition = "tinyint(1) default 0")
    private Boolean enabled;
}
