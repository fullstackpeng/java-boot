package com.fullstackpeng.module.system.basic.repository;

import com.fullstackpeng.common.data.jpa.core.repository.BaseJpaRepository;
import com.fullstackpeng.module.system.basic.domain.entity.BasRole;
import com.fullstackpeng.module.system.basic.domain.entity.BasRolePermission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;


@Repository
public interface BasRoleRepository extends BaseJpaRepository<BasRole, String> {

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return .
     */
    @Query("SELECT r FROM BasRole r JOIN BasUserRole ur ON r.id = ur.roleId WHERE ur.userId = ?1")
    List<BasRole> findByUserId(String userId);

    /**
     * 根据商户码获取角色ID
     *
     * @param sysCodes .
     * @return .
     */
    @Query("SELECT r.id FROM BasRole r WHERE r.sysCode IN ?1 AND r.system = true")
    Set<String> findAdminRoleIdsBySysCodes(List<String> sysCodes);


    /**
     * 根据系统编码查询角色ID
     *
     * @param sysCodes .
     * @return .
     */
    @Query("SELECT r.id FROM BasRole r WHERE r.sysCode IN ?1")
    Set<String> findRoleIdsBySysCodes(List<String> sysCodes);


    /**
     * 根据角色ID查询权限
     *
     * @param roleIds 角色ID
     * @return .
     */
    @Query("SELECT rp FROM BasRolePermission rp WHERE rp.roleId IN ?1")
    List<BasRolePermission> findRolePermissionsByRoleIds(Collection<String> roleIds);

    /**
     * 根据系统编码和编码查询是否存在
     *
     * @param sysCode .
     * @param code    .
     * @return .
     */
    boolean existsBySysCodeAndCode(String sysCode, String code);

    /**
     * 根据系统编码和编码查询是否存在
     *
     * @param sysCode .
     * @param code    .
     * @param id      .
     * @return .
     */
    boolean existsBySysCodeAndCodeAndIdNot(String sysCode, String code, String id);

    /**
     * 根据角色ID查询权限ID
     *
     * @param roleId 角色ID
     * @return .
     */
    @Query("SELECT rp.permissionId FROM BasRolePermission rp WHERE rp.roleId = ?1")
    List<String> getPermissionIdsByRoleId(String roleId);

}
