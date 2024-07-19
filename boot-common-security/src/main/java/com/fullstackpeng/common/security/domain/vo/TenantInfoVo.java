package com.fullstackpeng.common.security.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 商户信息
 */
@Data
@EqualsAndHashCode
@ToString
public class TenantInfoVo implements Serializable {
    /**
     * 商户名称
     */
    private String name;
    /**
     * 商户logo
     */
    private String logo;

}
