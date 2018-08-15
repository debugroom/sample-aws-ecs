package org.debugroom.sample.aws.ecs.backend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({InfraConfig.class, HSQLConfig.class})
public class TestConfig {
}
