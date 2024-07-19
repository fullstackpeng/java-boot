package com.fullstackpeng.module.system.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fullstackpeng.common.api.JsfPage;
import com.fullstackpeng.common.base.exception.ServiceException;
import com.fullstackpeng.common.data.jpa.core.service.BaseService;
import com.fullstackpeng.common.data.jpa.query.QueryHelper;
import com.fullstackpeng.module.system.common.domain.vo.SelectOptionVo;
import com.fullstackpeng.module.system.sys.domain.dto.DictItemDto;
import com.fullstackpeng.module.system.sys.domain.dto.DictSmallDto;
import com.fullstackpeng.module.system.sys.domain.entity.SysDict;
import com.fullstackpeng.module.system.sys.domain.entity.SysDictItem;
import com.fullstackpeng.module.system.sys.domain.query.DictItemQuery;
import com.fullstackpeng.module.system.sys.repository.SysDictItemRepository;
import com.fullstackpeng.module.system.sys.service.mapstruct.SysDictItemMapstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class SysDictItemService extends BaseService<SysDictItemRepository, SysDictItem, String> {
    private final SysDictItemMapstruct mapstruct;
    private final SysDictService dictService;

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 分页数据
     */
    public JsfPage<DictItemDto> page(DictItemQuery query) {
        Pageable page = QueryHelper.toPage(query);
        Specification<SysDictItem> specification = QueryHelper.ofBean(query);
        Page<SysDictItem> pageData = baseRepository.findAll(specification, page);
        List<DictItemDto> res = mapstruct.toDtoList(pageData.getContent());
        return QueryHelper.toJsfPage(pageData, res);
    }

    /**
     * 查询
     *
     * @param query 查询条件
     * @return 数据
     */
    public List<DictItemDto> list(DictItemQuery query) {
        Specification<SysDictItem> specification = QueryHelper.ofBean(query);
        List<SysDictItem> list = baseRepository.findAll(specification);
        return mapstruct.toDtoList(list);
    }


    /**
     * 根据字典类型查询
     *
     * @param type 字典类型
     * @return 数据
     */
    public List<SelectOptionVo> selectOption(String type) {
        DictItemQuery query = new DictItemQuery();
        query.setEnabled(true);
        query.setDictType(type);
        List<DictItemDto> list = list(query);
        return mapstruct.toSelectOption(list);
    }

    /**
     * 保存
     *
     * @param dto 数据
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(DictItemDto dto) {
        DictSmallDto dict = dto.getDict();
        if (null == dict) {
            throw new ServiceException("字典类型不能为空");
        }
        SysDict sysDict = dictService.findById(dict.getId());
        if (null == sysDict) {
            throw new ServiceException("字典类型不存在");
        }

        SysDictItem entity = mapstruct.toEntity(dto);
        entity.setDict(sysDict);
        entity.setDictType(sysDict.getType());
        save(entity);
    }

    /**
     * 更新
     *
     * @param dto 数据
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateById(DictItemDto dto) {
        DictSmallDto dict = dto.getDict();
        if (null == dict) {
            throw new ServiceException("字典类型不能为空");
        }
        SysDict sysDict = dictService.findById(dict.getId());
        if (null == sysDict) {
            throw new ServiceException("字典类型不存在");
        }
        String id = dto.getId();
        if (null == id) {
            throw new ServiceException("id不能为空");
        }
        SysDictItem entity = baseRepository.findById(id).orElseThrow(() -> new ServiceException("数据不存在"));

        BeanUtil.copyProperties(dto, entity, CopyOptions.create().ignoreNullValue());

        entity.setDict(sysDict);
        entity.setDictType(sysDict.getType());
        updateById(entity);
    }

    /**
     * 根据ID删除
     *
     * @param id id
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        SysDictItem dictItem = getById(id);
        if (null == dictItem) {
            throw new ServiceException("数据不存在");
        }
        remove(dictItem);
    }

}
