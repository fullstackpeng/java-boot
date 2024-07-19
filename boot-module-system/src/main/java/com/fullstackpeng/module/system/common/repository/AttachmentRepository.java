package com.fullstackpeng.module.system.common.repository;

import com.fullstackpeng.common.data.jpa.core.repository.BaseJpaRepository;
import com.fullstackpeng.module.system.common.domain.entity.Attachment;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends BaseJpaRepository<Attachment, String> {
}
