package com.fullstackpeng.common.oss.core.aliyun;

import com.fullstackpeng.common.oss.core.OssProperties;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AliyunOssProperties implements OssProperties {
    /**
     * 访问key
     */
    private String accessKey;
    /**
     * 访问密钥
     */
    private String secretKey;
    /**
     * 区域
     */
    private String region = "Auto";
    /**
     * endpoint
     */
    private String endpoint;
    /**
     * 协议
     */
    private Protocol endpointProtocol = Protocol.HTTPS;
    /**
     * endpoint 策略,默认 `public-read` 公共读
     */
    private String endpointPolicy = "public-read";
    /**
     * bucketName
     */
    private String bucketName;
    /**
     * 自定义域名
     */
    private String customDomain;

    /*=================STS相关参数====================*/
    /**
     * STS角色 ID
     */
    private String roleArn;
    /**
     * STS角色会话名称
     */
    private String roleSessionName;


}
