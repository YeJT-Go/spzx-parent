package com.y1jt.spzxmanager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : YeJT
 * @date : 2023-12-08 16:24
 * @Description: TODO
 */
@Data
@ConfigurationProperties(prefix = "spzx.minio")
public class MinioOProperties {

    private String accessKey;

    private String secretKey;

    private String bucketName;

    private String endpoint;

}
