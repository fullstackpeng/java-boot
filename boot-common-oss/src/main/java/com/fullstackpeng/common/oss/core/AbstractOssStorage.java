package com.fullstackpeng.common.oss.core;

import com.fullstackpeng.common.base.exception.OssException;
import com.fullstackpeng.common.base.utils.StrUtil;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class AbstractOssStorage implements OssStorage {
    protected OssProperties ossProperties;

    public AbstractOssStorage() {
    }

    public AbstractOssStorage(OssProperties ossProperties) {
        this.ossProperties = ossProperties;
    }

    /**
     * 获取客户端
     *
     * @param <T> 客户端
     * @return 客户端
     */
    @Nullable
    public abstract <T> T getClient();


    /**
     * 值是否存在
     *
     * @param val     值
     * @param message 提示信息
     * @return this
     */
    public AbstractOssStorage has(String val, String message) {
        if (StrUtil.isBlank(val)) {
            throw new OssException(message);
        }
        return this;
    }

    /**
     * 值是否存在
     *
     * @param val     值
     * @param message 提示信息
     * @return this
     */
    public AbstractOssStorage has(Object val, String message) {
        if (null == val) {
            throw new OssException(message);
        }
        return this;
    }

}
