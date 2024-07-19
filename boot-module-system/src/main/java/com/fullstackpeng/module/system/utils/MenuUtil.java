package com.fullstackpeng.module.system.utils;

import com.fullstackpeng.common.base.enums.MenuTypeEnums;
import com.fullstackpeng.common.base.utils.CollectionUtil;
import com.fullstackpeng.common.base.utils.TreeUtil;
import com.fullstackpeng.module.system.sys.domain.dto.PermissionDto;
import com.fullstackpeng.module.system.sys.domain.vo.MenuTreeVO;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;


public class MenuUtil {
    /**
     * 构建菜单树
     *
     * @param dtoList .
     *                * @param filterButton 是否过滤按钮
     * @return .
     */
    public static List<MenuTreeVO> buildMenuTree(List<PermissionDto> dtoList, boolean filterButton) {
        if (CollectionUtil.isEmpty(dtoList)) {
            return null;
        }
        List<PermissionDto> tree = buildTree(dtoList, filterButton);
        if (CollectionUtil.isEmpty(tree)) {
            return null;
        }
        return _buildMenuTree(tree);
    }

    /**
     * 构建树
     *
     * @param dataList     数据
     * @param filterButton 是否过滤按钮
     * @return 树
     */
    private static List<PermissionDto> buildTree(List<PermissionDto> dataList, boolean filterButton) {
        if (CollectionUtil.isEmpty(dataList)) {
            return null;
        }
        if (filterButton) {
            dataList.removeIf(permissionDto -> MenuTypeEnums.Menu_4.getValue().equals(permissionDto.getMenuType()));
        }
        if (CollectionUtil.isEmpty(dataList)) {
            return null;
        }
        return TreeUtil.buildTree(dataList);
    }

    private static List<MenuTreeVO> _buildMenuTree(@Nonnull List<PermissionDto> dtoList) {
        List<MenuTreeVO> menuTree = new ArrayList<>();
        dtoList.forEach(
                item -> {
                    MenuTreeVO tree = new MenuTreeVO();
                    tree.setId(item.getId());
                    tree.setParentId(item.getParentId());
                    tree.setTitle(item.getTitle());
                    tree.setId(item.getId());
                    //子类
                    List<PermissionDto> children = item.getChildren();
                    if (CollectionUtil.isNotEmpty(children)) {
                        tree.setChildren(_buildMenuTree(children));
                    }
                    menuTree.add(tree);
                }
        );
        return menuTree;
    }

}
