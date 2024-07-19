package com.fullstackpeng.common.security.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
public class UserOrgInfoDto implements Serializable {
    /**
     * 机构ID
     */
    private String id;
    /**
     * 机构名称
     */
    private String name;
    /**
     * 机构path
     */
    private String path;
}
