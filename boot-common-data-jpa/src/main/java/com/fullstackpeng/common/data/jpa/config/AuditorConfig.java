package com.fullstackpeng.common.data.jpa.config;

import com.fullstackpeng.common.base.context.UserContext;
import com.fullstackpeng.common.base.context.UserInfo;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class AuditorConfig implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        UserInfo userInfo = UserContext.get();
        if (null != userInfo) {
            return Optional.of(userInfo.getUsername());
        }
        return Optional.empty();
    }
}
