package com.fullstackpeng.module.system.sys.repository;

import com.fullstackpeng.common.data.jpa.core.repository.BaseJpaRepository;
import com.fullstackpeng.module.system.sys.domain.entity.SysNotice;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SysNoticeRepository extends BaseJpaRepository<SysNotice, String> {

    /**
     * 统计不在ids中的数量
     *
     * @param ids .
     * @return .
     */
    int countByIdNotInAndEnabledIsTrue(List<String> ids);

    /**
     * 查询不在ids中的数据
     *
     * @param ids .
     * @return .
     */
    List<SysNotice> findByIdNotInAndEnabledIsTrue(List<String> ids);

    /**
     * 统计启用的数量
     *
     * @return .
     */
    int countByEnabledIsTrue();

    /**
     * 查询启用的数据
     *
     * @return .
     */
    List<SysNotice> findByEnabledIsTrue();


    /**
     * 查询启用的数据
     *
     * @param ids .
     * @return .
     */
    List<SysNotice> findByEnabledIsTrueAndIdIn(List<String> ids);
}
