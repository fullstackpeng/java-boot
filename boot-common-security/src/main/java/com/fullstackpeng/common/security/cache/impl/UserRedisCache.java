package com.fullstackpeng.common.security.cache.impl;

import cn.hutool.core.util.RandomUtil;
import com.fullstackpeng.common.base.utils.JsonUtil;
import com.fullstackpeng.common.base.utils.StrUtil;
import com.fullstackpeng.common.cache.core.BootAdminCache;
import com.fullstackpeng.common.security.cache.UserProvider;
import com.fullstackpeng.common.security.config.SecurityProperties;
import com.fullstackpeng.common.security.domain.dto.UserInfoDto;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserRedisCache implements UserProvider {
    @Lazy
    @Resource
    private SecurityProperties properties;
    @Lazy
    @Resource
    private BootAdminCache cache;

    @Override
    public boolean putUser(String key, UserInfoDto dto) {
        if (StrUtil.isBlank(key) || dto == null) {
            return false;
        }
        String cacheKey = getCacheKey(key);
        String userJson = JsonUtil.DEFAULT.toJson(dto);
        // 避免token过期时间和用户信息过期时间一致
        int expireTime = properties.getTokenValidity() + RandomUtil.randomInt(900, 1800);
        return cache.setString(cacheKey, userJson, expireTime);
    }

    @Override
    public UserInfoDto getUser(String key) {
        if (StrUtil.isBlank(key)) {
            return null;
        }
        String cacheKey = getCacheKey(key);
        String userJson = cache.getString(cacheKey);
        if (StrUtil.isNotBlank(userJson)) {
            return JsonUtil.DEFAULT.json2Obj(userJson, UserInfoDto.class);
        }
        return null;
    }

    @Override
    public boolean removeUser(String key) {
        if (StrUtil.isBlank(key)) {
            return false;
        }
        String cacheKey = getCacheKey(key);
        cache.del(cacheKey);
        return true;
    }

    private String getCacheKey(String key) {
        return "login_info" + ":" + key;
    }
}