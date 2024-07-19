package com.fullstackpeng.module.system.sys.domain.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fullstackpeng.common.api.BaseQuery;
import com.fullstackpeng.common.data.jpa.query.core.Equals;
import com.fullstackpeng.common.data.jpa.query.core.GreaterThanEqual;
import com.fullstackpeng.common.data.jpa.query.core.LessThanEqual;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;


@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class NoticeQuery extends BaseQuery {
    /**
     * 是否启用
     */
    @Equals
    private Boolean enabled;

    /**
     * 公告开始时间
     */
    @GreaterThanEqual
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date noticeTimeStart;

    /**
     * 公告结束时间
     */
    @LessThanEqual
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date noticeTimeEnd;
}
