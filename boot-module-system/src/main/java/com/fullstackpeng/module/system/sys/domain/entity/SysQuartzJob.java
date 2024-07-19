package com.fullstackpeng.module.system.sys.domain.entity;

import com.fullstackpeng.common.data.jpa.core.domain.BaseEntity;
import com.fullstackpeng.common.data.jpa.core.identifier.IdGenerator;
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
@Table(name = "sys_quartz_job")
public class SysQuartzJob extends BaseEntity {
    @Id
    @IdGenerator
    private String id;

    /**
     * 任务类名
     */
    @NotBlank(message = "任务名不能为空")
    private String jobName;
    /**
     * 任务类名
     */

    @NotBlank(message = "任务类名/bean名称不能为空")
    private String jobClassName;
    /**
     * 参数
     */
    private String parameter;

    /**
     * cron表达式
     */
    @NotBlank(message = "cron表达式不能为空")
    private String cronExpression;

    /**
     * 商户识别码
     */
    private String sysCode;

    /**
     * 状态
     */
    @Column(name = "is_enabled", columnDefinition = "tinyint(1) default 1")
    private Boolean enabled = true;

    /**
     * 描述
     */
    private String description;
}
