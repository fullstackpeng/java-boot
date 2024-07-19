package com.fullstackpeng.common.security.service;

import com.fullstackpeng.common.security.domain.dto.UserInfoDto;
import jakarta.annotation.Nullable;

import java.util.Optional;

public interface UserLoginService {

    /**
     * 根据用户名获取商户识别码
     *
     * @param username 用户名
     * @return 商户识别码
     */
    Optional<String> getSysCodeByUsername(String username);

    /**
     * 根据用户名获取用户信息,管理端登录
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserInfoDto findByUsername(String username);

    /**
     * 根据用户名获取用户信息，商户端登录
     *
     * @param username 用户名
     * @param sysCode  商户识别码
     * @return 用户信息
     */
    UserInfoDto findByUsername(String username, @Nullable String sysCode);
}
