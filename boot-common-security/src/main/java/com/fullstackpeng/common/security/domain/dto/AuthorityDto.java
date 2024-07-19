package com.fullstackpeng.common.security.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityDto implements GrantedAuthority {
    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
