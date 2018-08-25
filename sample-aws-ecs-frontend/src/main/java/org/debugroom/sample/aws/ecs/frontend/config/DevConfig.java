package org.debugroom.sample.aws.ecs.frontend.config;

import org.debugroom.sample.aws.ecs.frontend.app.adapter.ConnectServiceProvider;
import org.debugroom.sample.aws.ecs.frontend.app.adapter.RandomAccessServiceProvider;
import org.debugroom.sample.aws.ecs.frontend.app.adapter.ServicePathHolder;
import org.debugroom.sample.aws.ecs.frontend.app.adapter.local.PropertyPathHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Profile("dev")
@Configuration
@PropertySource("classpath:local-service.properties")
public class DevConfig {

    @Bean
    public ServicePathHolder servicePathHolder(){
        return new PropertyPathHolder();
    }

    @Bean
    public ConnectServiceProvider connectServiceProvider(){
        return new RandomAccessServiceProvider();
    }

}
