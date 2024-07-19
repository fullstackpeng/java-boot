package com.fullstackpeng.common.data.jpa.core.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.TenantId;

@Getter
@Setter
@Accessors(chain = true)
@MappedSuperclass
public class BaseTenantEntity extends BaseEntity {

    /**
     * 租户字段
     */
    @TenantId
    private String sysCode;
}
