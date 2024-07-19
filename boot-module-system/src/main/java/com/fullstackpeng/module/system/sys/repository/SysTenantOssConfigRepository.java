package com.fullstackpeng.module.system.sys.repository;

import com.fullstackpeng.common.data.jpa.core.repository.BaseJpaRepository;
import com.fullstackpeng.module.system.sys.domain.entity.SysOssConfig;
import org.springframework.stereotype.Repository;


@Repository
public interface SysTenantOssConfigRepository extends BaseJpaRepository<SysOssConfig, String> {

    /**
     * 根据商户编码查询
     *
     * @param sysCode 商户编码
     * @return SysOssConfig
     */
    SysOssConfig findBySysCode(String sysCode);
}
