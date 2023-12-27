package com.y1jt.spzxmanager;

import com.y1jt.spzxmanager.properties.MinioOProperties;
import com.y1jt.spzxmanager.properties.UserProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yejintao
 */
@ComponentScan(basePackages = {"com.y1jt"})
@SpringBootApplication
@EnableConfigurationProperties(value = {UserProperties.class, MinioOProperties.class})
public class SpzxManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpzxManagerApplication.class, args);
    }
}
