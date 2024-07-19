package com.fullstackpeng.module.system.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fullstackpeng.common.base.utils.StrUtil;
import com.fullstackpeng.common.data.jpa.core.service.BaseService;
import com.fullstackpeng.module.system.sys.domain.dto.OssConfigDto;
import com.fullstackpeng.module.system.sys.domain.entity.SysOssConfig;
import com.fullstackpeng.module.system.sys.repository.SysTenantOssConfigRepository;
import com.fullstackpeng.module.system.sys.service.cache.OssCache;
import com.fullstackpeng.module.system.sys.service.mapstruct.SysOssConfigMapstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class SysTenantOssConfigService extends BaseService<SysTenantOssConfigRepository, SysOssConfig, String> {
    private final SysOssConfigMapstruct mapstruct;
    private final OssCache ossCache;

    /**
     * 根据商户编码查询
     *
     * @param sysCode 商户编码
     * @return SysOssConfig
     */
    public OssConfigDto findBySysCode(String sysCode) {
        Optional<SysOssConfig> ossConfigOptional = ossCache.get(sysCode);
        if (ossConfigOptional.isPresent()) {
            return mapstruct.toDto(ossConfigOptional.get());
        }
        SysOssConfig sysOssConfig = baseRepository.findBySysCode(sysCode);
        if (null != sysOssConfig) {
            ossCache.set(sysOssConfig);
        }
        return mapstruct.toDto(sysOssConfig);
    }

    /**
     * 保存
     *
     * @param dto dto
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(OssConfigDto dto) {
        SysOssConfig sysOssConfig = mapstruct.toEntity(dto);
        SysOssConfig entity = getById(dto.getId());
        if (StrUtil.isBlank(dto.getId())) {
            entity = sysOssConfig;
        } else {
            BeanUtil.copyProperties(sysOssConfig, entity, CopyOptions.create().setIgnoreNullValue(true));
        }
        save(entity);
        ossCache.set(entity);
    }
}
