package com.fullstackpeng.common.security.domain.vo;

import java.io.Serializable;
import java.util.List;

public interface TreeVO<T> extends Serializable {

    /**
     * 获取子类
     *
     * @return .
     */
    public List<T> getChildren();

    /**
     * 设置子类
     *
     * @param children .
     */
    void setChildren(List<T> children);
}
