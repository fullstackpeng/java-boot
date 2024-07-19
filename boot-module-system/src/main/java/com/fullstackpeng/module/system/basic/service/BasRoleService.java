package com.fullstackpeng.module.system.basic.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fullstackpeng.common.api.JsfPage;
import com.fullstackpeng.common.base.exception.ServiceException;
import com.fullstackpeng.common.base.utils.CollectionUtil;
import com.fullstackpeng.common.base.utils.StrUtil;
import com.fullstackpeng.common.data.jpa.core.service.BaseService;
import com.fullstackpeng.common.data.jpa.query.QueryHelper;
import com.fullstackpeng.module.system.basic.domain.dto.BasRoleDto;
import com.fullstackpeng.module.system.basic.domain.entity.BasOrg;
import com.fullstackpeng.module.system.basic.domain.entity.BasPermission;
import com.fullstackpeng.module.system.basic.domain.entity.BasRole;
import com.fullstackpeng.module.system.basic.domain.entity.BasRolePermission;
import com.fullstackpeng.module.system.basic.domain.query.BasRoleQuery;
import com.fullstackpeng.module.system.basic.repository.BasRoleRepository;
import com.fullstackpeng.module.system.basic.repository.BasUserRepository;
import com.fullstackpeng.module.system.basic.service.mapstruct.BasRoleMapstruct;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class BasRoleService extends BaseService<BasRoleRepository, BasRole, String> {
    @Lazy
    @Resource
    private BasUserRepository userRepository;
    private final BasRoleMapstruct mapstruct;

    /**
     * 根据用户ID获取角色
     *
     * @param userId 用户ID
     * @return 角色
     */
    public List<BasRole> findByUserId(String userId) {
        return baseRepository.findByUserId(userId);
    }

    /**
     * 根据系统编码获取管理员角色
     *
     * @param sysCodes 系统编码
     * @return 角色
     */
    public Set<String> findAdminRoleIdsBySysCodes(List<String> sysCodes) {
        return baseRepository.findAdminRoleIdsBySysCodes(sysCodes);
    }

    /**
     * 根据商户码获取角色ID
     *
     * @param sysCode .
     * @return .
     */
    public Set<String> findRoleIdsBySysCodes(List<String> sysCode) {
        return baseRepository.findRoleIdsBySysCodes(sysCode);
    }

    /**
     * 根据角色ID获取权限
     *
     * @param roleIds 角色ID
     * @return 权限
     */
    public List<BasRolePermission> findRolePermissionsByRoleIds(Collection<String> roleIds) {
        return baseRepository.findRolePermissionsByRoleIds(roleIds);
    }

    /**
     * code是否存在
     *
     * @param code code
     * @param id   需要排除的id
     * @return 是否存在
     */
    public Boolean existsCode(String code, String sysCode, String id) {
        if (StrUtil.isBlank(id)) {
            return baseRepository.existsBySysCodeAndCode(code, sysCode);
        }
        return baseRepository.existsBySysCodeAndCodeAndIdNot(code, sysCode, id);
    }

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页
     */
    public JsfPage<BasRoleDto> page(BasRoleQuery query) {
        Specification<BasRole> specification = QueryHelper.ofBean(query);
        Pageable page = QueryHelper.toPage(query);
        Page<BasRole> pageData = baseRepository.findAll(specification, page);
        List<BasRoleDto> res = mapstruct.toDtoList(pageData.getContent());
        return QueryHelper.toJsfPage(pageData, res);
    }

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 角色
     */
    public List<BasRoleDto> list(BasRoleQuery query) {
        Specification<BasRole> specification = QueryHelper.ofBean(query);
        List<BasRole> res = baseRepository.findAll(specification);
        return mapstruct.toDtoList(res);
    }

    /**
     * 根据角色ID获取权限ID
     *
     * @param roleId 角色ID
     * @return 权限ID
     */
    public List<String> getPermissionIdsByRoleId(String roleId) {
        return baseRepository.getPermissionIdsByRoleId(roleId);
    }

    /**
     * 保存
     *
     * @param dto 角色
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(BasRoleDto dto) {
        BasRole entity = mapstruct.toEntity(dto);
        Boolean existsCode = existsCode(entity.getCode(), dto.getSysCode(), entity.getId());
        if (existsCode) {
            throw new ServiceException("code已存在");
        }
        if (CollectionUtil.isEmpty(dto.getOrgIds())) {
            entity.setOrgs(null);
        } else {
            entity.setOrgs(dto.getOrgIds().stream().map(e -> {
                BasOrg org = new BasOrg();
                org.setId(e);
                return org;
            }).collect(Collectors.toList()));
        }
        save(entity);
    }

    /**
     * 更新
     *
     * @param dto 角色
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateById(BasRoleDto dto) {
        String id = dto.getId();
        if (StrUtil.isBlank(id)) {
            throw new ServiceException("id不能为空");
        }
        BasRole entity = getById(id);
        if (entity == null) {
            throw new ServiceException("数据不存在");
        }
        if (Boolean.TRUE.equals(entity.getSystem())) {
            throw new ServiceException("系统角色不允许修改");
        }
        BasRole update = mapstruct.toEntity(dto);
        BeanUtil.copyProperties(update, entity, CopyOptions.create().ignoreNullValue());
        if (CollectionUtil.isEmpty(dto.getOrgIds())) {
            entity.setOrgs(null);
        } else {
            entity.setOrgs(dto.getOrgIds().stream().map(e -> {
                BasOrg org = new BasOrg();
                org.setId(e);
                return org;
            }).collect(Collectors.toList()));
        }
        updateById(entity);
    }

    /**
     * 删除
     *
     * @param id 角色id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        BasRole entity = getById(id);
        if (entity == null) {
            throw new ServiceException("数据不存在");
        }
        if (Boolean.TRUE.equals(entity.getSystem())) {
            throw new ServiceException("系统角色不允许删除");
        }
        // 是否有用户使用
        if (userRepository.existsByRoleId(id)) {
            throw new ServiceException("有用户使用该角色，不允许删除");
        }

        // 删除角色&权限关联
        baseRepository.delete(entity);
    }

    /**
     * 重新授权
     *
     * @param roleId        角色id
     * @param permissionIds 权限id
     */
    @Transactional(rollbackFor = Exception.class)
    public void grantPermission(String roleId, List<String> permissionIds) {
        BasRole role = baseRepository.findById(roleId).orElseThrow(
                () -> new ServiceException("角色不存在"));

        if (CollectionUtil.isEmpty(permissionIds)) {
            role.setPermissions(null);
        } else {
            List<BasPermission> permissionList = permissionIds.stream().map(e -> {
                BasPermission permission = new BasPermission();
                permission.setId(e);
                return permission;
            }).collect(Collectors.toList());
            role.setPermissions(permissionList);
        }
        // 保存&更新 相关权限
        baseRepository.save(role);
    }

}
