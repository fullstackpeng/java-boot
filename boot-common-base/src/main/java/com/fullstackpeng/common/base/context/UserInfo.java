package com.fullstackpeng.common.base.context;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo implements Serializable {
    private String id;
    private String username;
    private String sysCode;
    /**
     * 数据权限
     */
    private List<String> dataScopes;
}
