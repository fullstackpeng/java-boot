package com.fullstackpeng.common.security.cache;


import com.fullstackpeng.common.base.utils.StrUtil;
import com.fullstackpeng.common.security.domain.dto.UserInfoDto;

public interface UserProvider {
    /**
     * 设置用户信息
     *
     * @param key key
     * @param dto 用户信息
     * @return 是否成功
     */
    boolean putUser(String key, UserInfoDto dto);

    /**
     * 获取用户信息
     *
     * @param key key
     * @return 用户信息
     */
    UserInfoDto getUser(String key);

    /**
     * 删除用户信息
     *
     * @param key key
     * @return 是否成功
     */
    boolean removeUser(String key);


    /**
     * 获取缓存key
     *
     * @param username 用户名
     * @param sysCode  系统编码
     * @return key
     */
    static String getCacheKey(String username, String sysCode) {
        if (StrUtil.isNotBlank(sysCode)) {
            return sysCode + ":" + username;
        }
        return username;
    }
}
