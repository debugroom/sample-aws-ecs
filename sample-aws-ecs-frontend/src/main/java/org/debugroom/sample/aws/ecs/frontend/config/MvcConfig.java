package org.debugroom.sample.aws.ecs.frontend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.debugroom.sample.aws.ecs.frontend.app.util.RequestBuilder;

@ComponentScan({"org.debugroom.sample.aws.ecs.frontend.app.web",
"org.debugroom.sample.aws.ecs.frontend.app.util"})
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

}
