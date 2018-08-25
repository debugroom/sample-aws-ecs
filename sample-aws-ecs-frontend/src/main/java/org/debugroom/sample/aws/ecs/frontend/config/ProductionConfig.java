package org.debugroom.sample.aws.ecs.frontend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Profile("production")
@Configuration
@PropertySource("classpath:ecs-service.properties")
public class ProductionConfig {
}
