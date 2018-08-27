package org.debugroom.sample.aws.ecs.frontend.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.aws.autoconfigure.cache.ElastiCacheAutoConfiguration;

@EnableConfigurationProperties
@SpringBootApplication(exclude={ElastiCacheAutoConfiguration.class})
public class WebApp {

    public static void main(String[] args) {
        SpringApplication.run(WebApp.class, args);
    }
}
