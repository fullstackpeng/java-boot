package com.fullstackpeng.module.system.basic.controller;

import com.fullstackpeng.common.api.JsfPage;
import com.fullstackpeng.common.base.R;
import com.fullstackpeng.common.security.domain.dto.UserInfoDto;
import com.fullstackpeng.common.security.domain.dto.UserOrgInfoDto;
import com.fullstackpeng.common.security.utils.SecurityUtil;
import com.fullstackpeng.module.system.basic.domain.dto.BasNoticeDto;
import com.fullstackpeng.module.system.basic.domain.query.BasNoticeQuery;
import com.fullstackpeng.module.system.basic.service.BasNoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bas/notice")
@Tag(name = "履约端：公告管理")
@RequiredArgsConstructor
public class BasNoticeController {
    private final BasNoticeService basNoticeService;

    /**
     * 查询未读消息数量
     *
     * @return 未读消息数量
     */
    @GetMapping("/unread/count")
    @Operation(summary = "未读统计")
    public R<Integer> countUnread() {
        UserInfoDto currentUser = SecurityUtil.getCurrentUser();
        if (currentUser == null) {
            return R.NG("用户未登录");
        }
        // 排除管理端
        UserOrgInfoDto orgInfo = currentUser.getOrgInfo();
        if (null == orgInfo) {
            return R.OK();
        }
        return R.OK(basNoticeService.countUnread(currentUser.getId()));
    }

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询公告")
    public R<JsfPage<BasNoticeDto>> page(BasNoticeQuery query) {
        UserInfoDto currentUser = SecurityUtil.getCurrentUser();
        if (currentUser == null) {
            return R.NG("用户未登录");
        }
        // 排除管理端
        UserOrgInfoDto orgInfo = currentUser.getOrgInfo();
        if (null == orgInfo) {
            return R.OK();
        }
        String tenantId = orgInfo.getPath().split(",")[0];
        query.setOrgId(tenantId);
        query.setUserId(currentUser.getId());
        JsfPage<BasNoticeDto> res = basNoticeService.page(query);
        return R.OK(res);
    }

    /**
     * 读公告
     *
     * @param noticeIds .
     * @return .
     */
    @PutMapping("/read")
    @Operation(summary = "读公告")
    public R<String> reads(@RequestBody List<String> noticeIds) {
        UserInfoDto currentUser = SecurityUtil.getCurrentUser();
        if (currentUser == null) {
            return R.OK();
        }
        basNoticeService.reads(currentUser.getId(), noticeIds);
        return R.OK();
    }

}
