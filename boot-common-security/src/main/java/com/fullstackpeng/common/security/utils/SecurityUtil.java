package com.fullstackpeng.common.security.utils;

import com.fullstackpeng.common.security.domain.dto.AuthorityDto;
import com.fullstackpeng.common.security.domain.dto.UserInfoDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class SecurityUtil {

    /**
     * 获取当前用户
     *
     * @return .
     */
    public static UserInfoDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        if (authentication.getPrincipal() instanceof UserInfoDto) {
            return (UserInfoDto) authentication.getPrincipal();
        }
        return null;
    }

    /**
     * 获取当前用户id
     *
     * @return .
     */
    public static Long getUserIdLong() {
        UserInfoDto currentUser = getCurrentUser();
        if (currentUser != null) {
            return Long.parseLong(currentUser.getId());
        }
        return null;
    }

    /**
     * 获取当前用户id
     *
     * @return .
     */
    public static String getUserId() {
        UserInfoDto currentUser = getCurrentUser();
        if (currentUser != null) {
            return currentUser.getId();
        }
        return null;
    }

    /**
     * 获取当前用户的系统编码
     *
     * @return .
     */
    public static String getSysCode() {
        UserInfoDto currentUser = getCurrentUser();
        if (currentUser != null) {
            return currentUser.getSysCode();
        }
        return null;
    }

    /**
     * 获取当前username
     *
     * @return .
     */
    public static String getUsername() {
        UserInfoDto currentUser = getCurrentUser();
        if (null != currentUser) {
            return currentUser.getUsername();
        }
        return null;
    }

    /**
     * 获取当前用户权限
     *
     * @return .
     */
    @SuppressWarnings("unchecked")
    public static Collection<AuthorityDto> getAuthorities() {
        UserDetails currentUser = getCurrentUser();
        if (currentUser != null) {
            return (Collection<AuthorityDto>) currentUser.getAuthorities();
        }
        return null;
    }
}
