package com.fullstackpeng.module.system.sys.repository;

import com.fullstackpeng.common.data.jpa.core.repository.BaseJpaRepository;
import com.fullstackpeng.module.system.sys.domain.entity.SysDict;
import org.springframework.stereotype.Repository;


@Repository
public interface SysDictRepository extends BaseJpaRepository<SysDict, String> {

    /**
     * 根据类型查询
     *
     * @param type 类型
     * @return 是否存在
     */
    boolean existsByType(String type);

    /**
     * 根据类型和id查询
     *
     * @param type 类型
     * @param id   id
     * @return 是否存在
     */
    boolean existsByTypeAndIdNot(String type, String id);
}
