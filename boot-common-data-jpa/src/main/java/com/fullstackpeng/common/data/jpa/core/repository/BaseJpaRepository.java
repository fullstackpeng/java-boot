package com.fullstackpeng.common.data.jpa.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseJpaRepository<E, ID> extends JpaRepository<E, ID>, JpaSpecificationExecutor<E> {
}
