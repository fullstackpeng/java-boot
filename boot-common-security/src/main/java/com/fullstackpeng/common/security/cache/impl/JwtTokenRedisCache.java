package com.fullstackpeng.common.security.cache.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import com.fullstackpeng.common.base.utils.DateUtil;
import com.fullstackpeng.common.base.utils.StrUtil;
import com.fullstackpeng.common.cache.core.BootAdminCache;
import com.fullstackpeng.common.security.cache.TokenProvider;
import com.fullstackpeng.common.security.config.SecurityProperties;
import com.fullstackpeng.common.security.domain.dto.UserInfoDto;
import com.fullstackpeng.common.security.utils.JwtUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenRedisCache implements TokenProvider {
    @Lazy
    @Resource
    private BootAdminCache cache;
    @Lazy
    @Resource
    private SecurityProperties properties;

    @Override
    public String createAccessToken(UserInfoDto dto) {
        String token = JwtUtil.sign(dto.getId(), dto.getUsername(), properties.getJwtSecret(), dto.getSysCode());
        cache.setString(getCacheKey(token), dto.getUsername(), properties.getTokenValidity());
        return token;
    }

    @Override
    public boolean checkRenewal(String accessToken) {
        if (!properties.isDelayToken()) {
            return false;
        }
        String cacheKey = getCacheKey(accessToken);
        long expire = cache.getExpire(cacheKey) * 1000;
        // 判断是否需要续期
        DateTime expireDate = DateUtil.offset(new Date(), DateField.MILLISECOND, (int) expire);
        long differ = expireDate.getTime() - System.currentTimeMillis();
        if (differ <= properties.getTokenDetect() * 1000) {
            cache.expire(cacheKey, properties.getTokenValidity());
            return true;
        }
        return false;
    }

    @Override
    public boolean removeToken(String accessToken) {
        String cacheKey = getCacheKey(accessToken);
        cache.del(cacheKey);
        return true;
    }

    @Override
    public String getValue(String accessToken) {
        return cache.getString(getCacheKey(accessToken));
    }

    private String getCacheKey(String token) {
        String prefix = properties.getTokenCachePrefix();
        if (StrUtil.isBlank(prefix)) {
            return token;
        }
        return prefix + ":" + token;
    }
}
