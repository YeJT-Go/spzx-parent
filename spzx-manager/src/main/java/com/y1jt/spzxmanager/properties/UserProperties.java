package com.y1jt.spzxmanager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author : YeJT
 * @date : 2023-11-29 17:36
 * @Description: TODO
 */
@Data
@ConfigurationProperties(prefix = "spzx.auth")
public class UserProperties {

    private List<String> noAuthUrls;

}
