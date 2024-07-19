package com.fullstackpeng.module.system.sys.repository;

import com.fullstackpeng.common.data.jpa.core.repository.BaseJpaRepository;
import com.fullstackpeng.module.system.sys.domain.entity.SysArea;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SysAreaRepository extends BaseJpaRepository<SysArea, String> {

    /**
     * 查询所有
     *
     * @return .
     */
    List<SysArea> findAllByEnabledIsTrueOrderByCreated();

    /**
     * code是否存在
     *
     * @param code code
     * @return true/false
     */
    boolean existsByCode(String code);

    /**
     * code是否存在
     *
     * @param code code
     * @param id   id
     * @return true/false
     */
    boolean existsByCodeAndIdNot(String code, String id);

    /**
     * 是否有子节点
     *
     * @param id id
     * @return true/false
     */
    default boolean hashChild(String id) {
        return existsByParentId(id);
    }

    /**
     * parentId是否存在
     *
     * @param parentId parentId
     * @return true/false
     */
    boolean existsByParentId(String parentId);


    /**
     * 更新子节点是否启用
     *
     * @param path    path
     * @param enabled 是否启用
     */
    @Modifying
    @Query("UPDATE SysArea SET enabled = ?2 WHERE path LIKE ?1%")
    void updateEnabledChild(String path, boolean enabled);
}
