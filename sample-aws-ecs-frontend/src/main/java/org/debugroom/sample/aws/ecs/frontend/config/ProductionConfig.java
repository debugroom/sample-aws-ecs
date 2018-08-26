package org.debugroom.sample.aws.ecs.frontend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import org.debugroom.sample.aws.ecs.frontend.app.adapter.ConnectServiceProvider;
import org.debugroom.sample.aws.ecs.frontend.app.adapter.ServicePathHolder;
import org.debugroom.sample.aws.ecs.frontend.app.adapter.SimpleAccessServiceProvider;
import org.debugroom.sample.aws.ecs.frontend.app.adapter.aws.EcsPathHolder;

@Profile("production")
@Configuration
@PropertySource("classpath:ecs-service.properties")
public class ProductionConfig {

    @Bean
    public ServicePathHolder servicePathHolder(){
        return new EcsPathHolder();
    }

    @Bean
    public ConnectServiceProvider connectServiceProvider(){
        return new SimpleAccessServiceProvider();
    }

}
