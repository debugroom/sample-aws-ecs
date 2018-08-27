package org.debugroom.sample.aws.ecs.backend.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.aws.autoconfigure.cache.ElastiCacheAutoConfiguration;

@SpringBootApplication(exclude={ElastiCacheAutoConfiguration.class})
public class WebApp {

    public static void main(String[] args) {
        SpringApplication.run(WebApp.class, args);
    }
}
