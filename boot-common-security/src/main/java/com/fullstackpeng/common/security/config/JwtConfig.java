package com.fullstackpeng.common.security.config;

import com.fullstackpeng.common.security.utils.JwtUtil;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    public JwtConfig(SecurityProperties securityProperties) {
        JwtUtil.setSecurityProperties(securityProperties);
    }
}
