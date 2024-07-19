package com.fullstackpeng.security.login;

import com.fullstackpeng.common.security.domain.vo.RouteVO;
import com.fullstackpeng.common.security.service.cache.UserRoutesCache;
import com.fullstackpeng.module.system.basic.service.BasPermissionService;
import com.fullstackpeng.module.system.sys.domain.dto.PermissionDto;
import com.fullstackpeng.module.system.sys.service.SysPermissionService;
import com.fullstackpeng.util.RouteUtil;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserRouterService implements com.fullstackpeng.common.security.service.UserRouterService  {
    private final SysPermissionService sysPermissionService;
    private final BasPermissionService basPermissionService;
    @Lazy
    @Resource
    private UserRoutesCache userRoutesCache;

    @Override
    public List<RouteVO> getRoutes(String userId) {
        return getCacheRoutes(userId, sysPermissionService::userRoutes);
    }

    @Override
    public List<RouteVO> getRoutes(String userId, String sysCode) {
        return getCacheRoutes(userId, basPermissionService::userRoutes);
    }

    private List<RouteVO> getCacheRoutes(String userId, Function<String, List<PermissionDto>> func) {
        Optional<List<RouteVO>> routeOptional = userRoutesCache.get(userId);
        if (routeOptional.isPresent()) {
            return routeOptional.get();
        }
        List<PermissionDto> permissions = func.apply(userId);
        List<RouteVO> routes = getRoutes(permissions);
        userRoutesCache.put(userId, routes);
        return routes;
    }


    /**
     * 获取路由
     *
     * @param permissions 权限
     * @return 路由
     */
    private List<RouteVO> getRoutes(List<PermissionDto> permissions) {
        if (null == permissions) {
            return List.of();
        }
        return RouteUtil.buildRoutes(permissions);
    }
}
