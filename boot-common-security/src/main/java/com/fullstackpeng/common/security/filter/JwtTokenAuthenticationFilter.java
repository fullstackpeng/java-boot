package com.fullstackpeng.common.security.filter;

import com.fullstackpeng.common.base.context.UserContext;
import com.fullstackpeng.common.base.utils.StrUtil;
import com.fullstackpeng.common.security.cache.TokenProvider;
import com.fullstackpeng.common.security.context.AuthenticationContextHolder;
import com.fullstackpeng.common.security.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

/**
 * token认证过滤器
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = JwtUtil.getToken(request);
            if (StrUtil.isNotBlank(token)) {
                // 判断是否过期
                String value = tokenProvider.getValue(token);
                if (StrUtil.isNotBlank(value) && SecurityContextHolder.getContext().getAuthentication() == null) {
                    Optional<String> usernameOptional = JwtUtil.getUsername(request);
                    if (usernameOptional.isEmpty()) {
                        filterChain.doFilter(request, response);
                        return;
                    }
                    UserDetails userDetails = userDetailsService.loadUserByUsername(usernameOptional.get());
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            token,
                            userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
                tokenProvider.checkRenewal(token);
            }
            filterChain.doFilter(request, response);
        } finally {
            AuthenticationContextHolder.clear();
            UserContext.remove();
        }
    }
}
