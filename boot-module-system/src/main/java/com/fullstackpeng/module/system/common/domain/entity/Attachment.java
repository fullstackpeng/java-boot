package com.fullstackpeng.module.system.common.domain.entity;

import com.fullstackpeng.common.data.jpa.core.domain.BaseEntity;
import com.fullstackpeng.common.data.jpa.core.identifier.IdGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 附件
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "t_attachment")
public class Attachment extends BaseEntity {
    @Id
    @IdGenerator
    private String id;
    /**
     * 文件名
     */
    private String displayName;
    /**
     * 文件类型
     */
    private String mediaType;
    /**
     * 文件大小
     */
    private Long size;
    /**
     * 链接
     */
    private String permalink;
    /**
     * 归属商户
     */
    private String sysCode;
}
