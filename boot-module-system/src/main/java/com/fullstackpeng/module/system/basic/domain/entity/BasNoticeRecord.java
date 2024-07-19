package com.fullstackpeng.module.system.basic.domain.entity;

import com.fullstackpeng.common.data.jpa.core.identifier.IdGenerator;
import com.fullstackpeng.module.system.sys.domain.entity.SysNotice;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "bas_notice_record")
public class BasNoticeRecord implements Serializable {
    @Id
    @IdGenerator
    private String id;

    /**
     * 公告ID
     */
    @OneToOne
    @JoinColumn(name = "notice_id")
    private SysNotice notice;
    /**
     * 用户ID
     */
    @OneToOne
    @JoinColumn(name = "user_id")
    private BasUser user;
    /**
     * 是否已读
     */
    @Column(name = "is_read", columnDefinition = "tinyint(1) default 0")
    private Boolean read;
    /**
     * 阅读时间
     */
    private Date readTime;
}
