package org.debugroom.sample.aws.ecs.frontend.config;

import org.debugroom.sample.aws.ecs.frontend.app.adapter.ConnectServiceProvider;
import org.debugroom.sample.aws.ecs.frontend.app.adapter.RandomAccessServiceProvider;
import org.debugroom.sample.aws.ecs.frontend.app.adapter.ServicePathHolder;
import org.debugroom.sample.aws.ecs.frontend.app.adapter.aws.DockerEnvironmentVariablesPathHolder;
import org.debugroom.sample.aws.ecs.frontend.app.adapter.aws.EcsPathHolder;
import org.debugroom.sample.aws.ecs.frontend.app.adapter.local.PropertyPathHolder;
import org.debugroom.sample.aws.ecs.frontend.app.util.RequestBuilder;
import org.debugroom.sample.aws.ecs.frontend.app.util.UtilProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class TestConfig {

    @Configuration
    @EnableConfigurationProperties
    public static class PropertyPathTestConfig{

        @Bean(name = "propertyPathHolder")
        ServicePathHolder servicePathHolder(){
            return (ServicePathHolder) new PropertyPathHolder();
        }

        @Bean(name = "propertyPathHolderProvider")
        ConnectServiceProvider connectServiceProvider(){
            return new RandomAccessServiceProvider();
        }

        @Bean
        RequestBuilder requestBuilder(){
            return new RequestBuilder();
        }

        @Bean
        UtilProperties utilProperties(){
            return new UtilProperties();
        }

    }

    @Configuration
    @EnableConfigurationProperties
    public static class EcsPathTestConfig{

        @Bean(name = "ecsPathHolder")
        ServicePathHolder servicePathHolder(){
            return new EcsPathHolder();
        }

        @Bean(name = "ecsPathHolderProvider")
        ConnectServiceProvider connectServiceProvider(){
            return new RandomAccessServiceProvider();
        }

        @Bean
        RequestBuilder requestBuilder(){
            return new RequestBuilder();
        }

        @Bean
        UtilProperties utilProperties(){
            return new UtilProperties();
        }

    }

    @Configuration
    @EnableConfigurationProperties
    public static class DockerEnvironmentVariablesTestConfig{

        @Bean(name = "dockerEnvironmentVariablesPathHolder")
        ServicePathHolder servicePathHolder(){
            return new DockerEnvironmentVariablesPathHolder();
        }

        @Bean(name = "dockerEnvironmentVariablesPathHolderProvider")
        ConnectServiceProvider connectServiceProvider(){
            return new RandomAccessServiceProvider();
        }

        @Bean
        RequestBuilder requestBuilder(){
            return new RequestBuilder();
        }

        @Bean
        UtilProperties utilProperties(){
            return new UtilProperties();
        }

    }

}
