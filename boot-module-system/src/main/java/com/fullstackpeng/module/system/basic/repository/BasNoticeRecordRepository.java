package com.fullstackpeng.module.system.basic.repository;

import com.fullstackpeng.common.data.jpa.core.repository.BaseJpaRepository;
import com.fullstackpeng.module.system.basic.domain.entity.BasNoticeRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BasNoticeRecordRepository extends BaseJpaRepository<BasNoticeRecord, String> {

    /**
     * 根据用户ID查询已读的公告ID
     *
     * @param userId .
     * @return .
     */
    @Query("SELECT notice.id FROM BasNoticeRecord WHERE user.id = ?1")
    List<String> findNoticeIdByUserId(String userId);
}
