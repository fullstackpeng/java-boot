package com.fullstackpeng.module.system.common.service;

import com.fullstackpeng.common.api.JsfPage;
import com.fullstackpeng.common.data.jpa.core.service.BaseService;
import com.fullstackpeng.common.data.jpa.query.QueryHelper;
import com.fullstackpeng.module.system.common.domain.dto.AttachmentDto;
import com.fullstackpeng.module.system.common.domain.entity.Attachment;
import com.fullstackpeng.module.system.common.domain.query.AttachmentQuery;
import com.fullstackpeng.module.system.common.repository.AttachmentRepository;
import com.fullstackpeng.module.system.common.service.mapstruct.AttachmentMapstruct;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttachmentService extends BaseService<AttachmentRepository, Attachment, String> {
    private final AttachmentMapstruct mapstruct;
    @Lazy
    @Resource
    private CommonsOssService commonsOssService;


    /**
     * 保存附件
     *
     * @param dto 附件信息
     */
    public void save(AttachmentDto dto) {
        Attachment attachment = mapstruct.toEntity(dto);
        baseRepository.save(attachment);
    }


    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    public JsfPage<AttachmentDto> page(AttachmentQuery query) {
        Pageable page = QueryHelper.toPage(query);
        Specification<Attachment> specification = QueryHelper.ofBean(query);
        Page<Attachment> pageData = baseRepository.findAll(specification, page);

        List<AttachmentDto> res = mapstruct.toDtoList(pageData.getContent());
        return QueryHelper.toJsfPage(pageData, res);
    }

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 数据
     */
    public List<AttachmentDto> list(AttachmentQuery query) {
        Specification<Attachment> specification = QueryHelper.ofBean(query);
        List<Attachment> list = baseRepository.findAll(specification);
        return mapstruct.toDtoList(list);
    }

    /**
     * 删除
     *
     * @param id 主键
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        Optional<Attachment> optionalAttachment = baseRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            commonsOssService
                    .getOssStorage(attachment.getSysCode())
                    .ifPresent(ossStorage -> {
                        ossStorage.deleteUrl(attachment.getPermalink());
                    });
            baseRepository.deleteById(id);
        }
    }
}
