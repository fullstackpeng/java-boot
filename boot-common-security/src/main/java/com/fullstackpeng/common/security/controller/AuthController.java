package com.fullstackpeng.common.security.controller;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import com.fullstackpeng.common.base.R;
import com.fullstackpeng.common.base.utils.StrUtil;
import com.fullstackpeng.common.security.cache.TokenProvider;
import com.fullstackpeng.common.security.config.SecurityProperties;
import com.fullstackpeng.common.security.context.AuthenticationContextHolder;
import com.fullstackpeng.common.security.controller.mapstruct.LoginInfoMapstruct;
import com.fullstackpeng.common.security.domain.dto.UserInfoDto;
import com.fullstackpeng.common.security.domain.vo.LoginInfoVo;
import com.fullstackpeng.common.security.domain.vo.RouteVO;
import com.fullstackpeng.common.security.service.UserRouterService;
import com.fullstackpeng.common.security.utils.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@Slf4j
@Tag(name = "系统：授权接口")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final SecurityProperties securityProperties;
    private final LoginInfoMapstruct loginInfoMapper;
    private final UserRouterService userRouterService;


    /**
     * 登录
     *
     * @return 登录信息
     */
    @PostMapping("/login")
    @Operation(summary = "登录")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "登录成功"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "登录异常",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = R.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "认证失败",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = R.class))
            )
    })
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "username", description = "用户名", required = true),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "密码", required = true)
    public R<LoginInfoVo> loginByUsername(@RequestParam String username, @RequestBody String password) {
        // 公钥加密私钥解密
        password = SecureUtil.rsa(securityProperties.getLogin().getRsaPrivateKey(), securityProperties.getLogin().getRsaPublicKey()).decryptStr(
                password,
                KeyType.PrivateKey
        );
        try {
            // 登录认证
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                    password);
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            UserInfoDto user = (UserInfoDto) authenticate.getPrincipal();
            // 生成令牌
            String accessToken = tokenProvider.createAccessToken(user);

            // 返回 token 与 用户信息
            LoginInfoVo res = loginInfoMapper.toDto(user);
            res.setToken(accessToken);
            return R.OK(res);
        } catch (AuthenticationException e) {
            log.error("登录异常:{}", e.getMessage());
            if (e instanceof UsernameNotFoundException) {
                return R.NG("用户不存在");
            }
            return R.NG(e.getMessage());
        } finally {
            AuthenticationContextHolder.clear();
        }
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/info")
    @Operation(summary = "获取用户信息")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "获取用户信息成功"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "获取用户信息异常",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = R.class))
            )
    })
    public R<LoginInfoVo> userInfo() {
        UserInfoDto currentUser = SecurityUtil.getCurrentUser();
        return R.OK(loginInfoMapper.toDto(currentUser));
    }

    /**
     * 获取用户路由
     */
    @GetMapping("/routes")
    @Operation(summary = "获取用户路由")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "获取用户路由成功"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "获取用户路由异常",
                    content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = R.class))
            )
    })
    public R<List<RouteVO>> routes() {
        UserInfoDto currentUser = SecurityUtil.getCurrentUser();
        if (currentUser == null) {
            return R.NG("获取当前用户失败");
        }
        if (StrUtil.isNotBlank(currentUser.getSysCode())) {
            return R.OK(userRouterService.getRoutes(currentUser.getId(), currentUser.getSysCode()));
        }
        return R.OK(userRouterService.getRoutes(currentUser.getId()));
    }

//    @PostMapping("/logout")
//    @Operation(summary = "退出登录")
//    @Deprecated
//    public R<String> logout(HttpServletRequest request) {
//        String token = JwtUtil.getToken(request);
//        tokenProvider.removeToken(token);
//        return R.OK("退出成功");
//    }

}
