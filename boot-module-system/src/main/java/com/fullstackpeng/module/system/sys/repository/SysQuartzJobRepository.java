package com.fullstackpeng.module.system.sys.repository;

import com.fullstackpeng.common.data.jpa.core.repository.BaseJpaRepository;
import com.fullstackpeng.module.system.sys.domain.entity.SysQuartzJob;
import org.springframework.stereotype.Repository;


@Repository
public interface SysQuartzJobRepository extends BaseJpaRepository<SysQuartzJob, String> {
}
