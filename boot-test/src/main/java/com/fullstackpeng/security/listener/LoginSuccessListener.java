package com.fullstackpeng.security.listener;

import com.fullstackpeng.common.base.utils.StrUtil;
import com.fullstackpeng.common.security.domain.dto.UserInfoDto;
import com.fullstackpeng.module.system.basic.service.BasUserService;
import com.fullstackpeng.module.system.sys.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class LoginSuccessListener {
    private final BasUserService basUserService;
    private final SysUserService sysUserService;

    /**
     * 登录成功
     *
     * @param event .
     */
    @EventListener
    @Async
    public void onLoginSuccess(AuthenticationSuccessEvent event) {
        log.info("登录成功监听器 登录事件:{}", event);
        Authentication authentication = event.getAuthentication();
        if (null == authentication) {
            log.info("登录成功监听器 登录事件 认证信息为空>>>>");
            return;
        }
        log.info("登录成功监听器 登录事件 认证信息:{}", authentication);
        if (authentication.getPrincipal() instanceof UserInfoDto user) {
            log.info("登录成功监听器 登录事件 用户信息:{}", user);
            changeLastLoginTime(user.getUsername(), user.getSysCode());
        }
    }

    private void changeLastLoginTime(String username, String sysCode) {
        log.info("修改最后登录时间:{}", username);
        if (StrUtil.isNotBlank(sysCode)) {
            basUserService.changeLastLoginTimeByUsername(username, sysCode);
        } else {
            sysUserService.changeLastLoginTimeByUsername(username);
        }
        log.info("修改最后登录时间完成:{}", username);
    }

}
