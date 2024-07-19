package com.fullstackpeng.module.system.sys.domain.entity;

import com.fullstackpeng.common.data.jpa.core.domain.BaseEntity;
import com.fullstackpeng.common.data.jpa.core.identifier.IdGenerator;
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
@Table(name = "sys_oss_config")
@Entity
public class SysOssConfig extends BaseEntity {

    @Id
    @IdGenerator
    private String id;
    /**
     * oss名称
     */
    @NotBlank(message = "oss名称不能为空")
    private String ossName;

    /**
     * oss类型
     */
    @NotBlank(message = "oss类型")
    private String ossType;
    /**
     * 访问key
     */
    @NotBlank(message = "访问key不能为空")
    private String accessKey;
    /**
     * 秘钥
     */
    @NotBlank(message = "秘钥不能为空")
    private String secretKey;
    /**
     * 桶名称
     */
    @NotBlank(message = "桶名称不能为空")
    private String bucketName;
    /**
     * 端点
     */
    @NotBlank(message = "端点不能为空")
    private String endpoint;
    /**
     * 域名
     */
    private String domain;
    /**
     * 区域
     */
    private String region;
    /**
     * 商户编码
     */
    private String sysCode;
    /**
     * 角色ID,用于STS临时授权
     */
    private String roleArn;
}
