package org.debugroom.sample.aws.ecs.frontend.app.adapter;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import org.debugroom.sample.aws.ecs.frontend.config.TestConfig;

@Slf4j
@RunWith(Enclosed.class)
public class SimpleAccessServiceProviderTest {

    @RunWith(SpringRunner.class)
    @ActiveProfiles("test")
    @TestPropertySource("classpath:ecs-service.properties")
    @SpringBootTest(classes = {TestConfig.SimpleAccessServiceProviderTestConfig.class})
    public static class PropertyPathProviderTest{

        @Autowired
        @Qualifier("simpleAccessServiceProvider")
        ConnectServiceProvider connectServiceProvider;

        @Test
        public void propertyPathProviderTest(){
            String frontendPath = connectServiceProvider.getAuthority("frontend");
            String backendPath = connectServiceProvider.getAuthority("backend");
            log.info("EcsPathProvider : " + frontendPath);
            log.info("EcsPathProvider : " + backendPath);

            assertThat(frontendPath, is(anyOf(equalTo("sample-ecs-alb-1379155413.ap-northeast-1.elb.amazonaws.com"))));
            assertThat(backendPath, is("sample-ecs-alb-1379155413.ap-northeast-1.elb.amazonaws.com"));
        }

    }
}
