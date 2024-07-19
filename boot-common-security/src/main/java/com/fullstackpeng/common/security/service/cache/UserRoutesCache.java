package com.fullstackpeng.common.security.service.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fullstackpeng.common.base.utils.JsonUtil;
import com.fullstackpeng.common.cache.core.BootAdminCache;
import com.fullstackpeng.common.cache.core.CacheUtil;
import com.fullstackpeng.common.security.domain.vo.RouteVO;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserRoutesCache implements CacheUtil {
    @Lazy
    @Resource
    private BootAdminCache cache;

    @Getter
    public enum KeyValue implements CacheUtil.KeyValue {
        /**
         * 用户路由
         */
        USER_ROUTES("user_routes", EXPIRE_TIME, "用户路由");
        private final String prefix;
        private final int expire;
        private final String desc;

        KeyValue(String prefix, int expire, String desc) {
            this.prefix = prefix;
            this.expire = expire;
            this.desc = desc;
        }
    }

    /**
     * 设置用户路由缓存
     *
     * @param key   key
     * @param value value
     */
    public void put(String key, List<RouteVO> value) {
        String json = JsonUtil.DEFAULT.toJson(value);
        String cacheKey = getCacheKey(KeyValue.USER_ROUTES, key);
        cache.setString(cacheKey, json, KeyValue.USER_ROUTES.getExpire());
    }

    /**
     * 获取用户路由缓存
     *
     * @param key key
     * @return 用户路由
     */
    public Optional<List<RouteVO>> get(String key) {
        String cacheKey = getCacheKey(KeyValue.USER_ROUTES, key);
        String json = cache.getString(cacheKey);
        if (null == json) {
            return Optional.empty();
        }
        List<RouteVO> res = JsonUtil.DEFAULT.json2Obj(json, new TypeReference<List<RouteVO>>() {
        });
        return Optional.of(res);
    }

    /**
     * 删除用户路由缓存
     *
     * @param key key
     */
    public void delete(String key) {
        String cacheKey = getCacheKey(KeyValue.USER_ROUTES, key);
        cache.del(cacheKey);
    }

    /**
     * 清空用户路由缓存
     */
    public void clear() {
        String cacheKey = getCacheKey(KeyValue.USER_ROUTES, "*");
        cache.scanKeys(cacheKey).ifPresent(cache::del);
    }
}
