package com.fullstackpeng.module.system.sys.repository;

import com.fullstackpeng.common.data.jpa.core.repository.BaseJpaRepository;
import com.fullstackpeng.module.system.sys.domain.entity.SysDictItem;
import org.springframework.stereotype.Repository;


@Repository
public interface SysDictItemRepository extends BaseJpaRepository<SysDictItem, String> {
}
