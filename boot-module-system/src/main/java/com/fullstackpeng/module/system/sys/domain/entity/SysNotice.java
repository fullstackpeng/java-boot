package com.fullstackpeng.module.system.sys.domain.entity;

import com.fullstackpeng.common.data.jpa.core.domain.BaseEntity;
import com.fullstackpeng.common.data.jpa.core.identifier.IdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;


@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "sys_notice")
public class SysNotice extends BaseEntity {

    @Id
    @IdGenerator
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 公告日期（起）
     */
    private Date noticeTimeStart;

    /**
     * 公告日期（止）
     */
    private Date noticeTimeEnd;

    /**
     * 是否启用  1启用  2禁用
     */
    @Column(name = "is_enabled", columnDefinition = "tinyint(1) default 1")
    private Boolean enabled = true;
    /**
     * 网点ID 多个以逗号隔开
     */
    private String orgId;
}
