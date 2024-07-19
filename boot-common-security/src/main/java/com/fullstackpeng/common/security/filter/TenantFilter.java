package com.fullstackpeng.common.security.filter;

import com.fullstackpeng.common.base.context.TenantContext;
import com.fullstackpeng.common.base.utils.StrUtil;
import com.fullstackpeng.common.security.service.UserLoginService;
import com.fullstackpeng.common.security.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class TenantFilter extends OncePerRequestFilter {
    private final UserLoginService userLoginService;
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/auth/login",
            "POST");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            String tenant = JwtUtil.getTenant(request);
            if (StrUtil.isNotBlank(tenant)) {
                TenantContext.set(tenant);
                chain.doFilter(request, response);
                return;
            }
            String token = JwtUtil.getToken(request);
            if (StrUtil.isNotBlank(token)) {
                Optional<String> tenantOptional = JwtUtil.getTenant(token);
                tenantOptional.ifPresent(TenantContext::set);
                chain.doFilter(request, response);
                return;
            }
            if (DEFAULT_ANT_PATH_REQUEST_MATCHER.matches(request)) {
                String username = obtainUsername(request);
                userLoginService.getSysCodeByUsername(username).ifPresent(TenantContext::set);
            }
            // 这里可以复制一下代码，不同登陆类型，根据不同的方式获取用户租户code
            chain.doFilter(request, response);
        } finally {
            TenantContext.remove();
        }
    }

    private String obtainUsername(HttpServletRequest request) {
        return request.getParameter("username");
    }
}
