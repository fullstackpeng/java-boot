package com.fullstackpeng.module.system.sys.repository;

import com.fullstackpeng.common.data.jpa.core.repository.BaseJpaRepository;
import com.fullstackpeng.module.system.sys.domain.entity.SysTenant;
import org.springframework.stereotype.Repository;


@Repository
public interface SysTenantRepository extends BaseJpaRepository<SysTenant, String> {

    /**
     * 根据商户编码查询
     *
     * @param sysCode 商户编码
     * @return 是否存在
     */
    boolean existsBySysCode(String sysCode);

    /**
     * 根据商户编码和ID查询
     *
     * @param sysCode 商户编码
     * @param id      ID
     * @return 是否存在
     */
    boolean existsBySysCodeAndIdNot(String sysCode, String id);
}
