package com.fullstackpeng.common.base.enums;

import lombok.Getter;

/**
 * oss类型
 */
@Getter
public enum OssTypeEnums implements ValueEnum<String> {
    /**
     * 阿里云
     */
    ALIYUN("aliyun", "阿里云"),
    /**
     * s3
     */
    S3("s3", "亚马逊S3"),
    ;

    private final String value;
    private final String name;

    OssTypeEnums(String value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public String getValue() {
        return value;
    }
}
