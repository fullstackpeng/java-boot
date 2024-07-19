package com.fullstackpeng.common.oss.configuration;

import com.fullstackpeng.common.oss.core.aliyun.AliyunOssProperties;
import com.fullstackpeng.common.oss.core.s3.S3OssProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
@Data
@ConfigurationProperties(prefix = "java.boot.oss")
public class OssProperties {
    /**
     * 是否启用
     */
    private boolean enabled;
    /**
     * 类型
     */
    private String type;
    /**
     * 阿里云oss
     */
    private AliyunOssProperties aliyun = new AliyunOssProperties();
    /**
     * s3
     */
    private S3OssProperties s3 = new S3OssProperties();
}
