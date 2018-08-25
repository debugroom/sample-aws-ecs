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
import org.debugroom.sample.aws.ecs.frontend.config.WebApp;

@Slf4j
@RunWith(Enclosed.class)
public class RandomAccessServiceProviderTest {

    @RunWith(SpringRunner.class)
    @ActiveProfiles("test")
    @TestPropertySource("classpath:local-service.properties")
    @SpringBootTest(classes = {TestConfig.PropertyPathTestConfig.class})
    public static class PropertyPathProviderTest{

        @Autowired
        @Qualifier("propertyPathHolderProvider")
        ConnectServiceProvider connectServiceProvider;

        @Test
        public void propertyPathProviderTest(){
            String frontendPath = connectServiceProvider.getAuthority("frontend");
            String backendPath = connectServiceProvider.getAuthority("backend");
            log.info("PropertyPathProvider : " + frontendPath);
            log.info("PropertyPathProvider : " + backendPath);

            assertThat(frontendPath, is(anyOf(
                    equalTo("localhost:8080"), equalTo("192.168.1.3:8080"))));
            assertThat(backendPath, is("localhost:8081"));
        }

    }

    @RunWith(SpringRunner.class)
    @ActiveProfiles("test")
    @SpringBootTest(classes = {TestConfig.EcsPathTestConfig.class})
    @TestPropertySource("classpath:ecs-service.properties")
    public static class EcsPathProviderTest{

        @Autowired
        @Qualifier( "ecsPathHolderProvider")
        ConnectServiceProvider connectServiceProvider;

        @Test
        public void ecsPathProviderTest(){
            String frontendPath = connectServiceProvider.getAuthority("frontend");
            String backendPath = connectServiceProvider.getAuthority("backend");
            log.info("EcsPathProvider : " + frontendPath);
            log.info("EcsPathProvider : " + backendPath);
            assertThat(frontendPath, is("sample-ecs-alb-1379155413.ap-northeast-1.elb.amazonaws.com"));
            assertThat(backendPath, is("sample-ecs-alb-1379155413.ap-northeast-1.elb.amazonaws.com"));
        }

    }

    @RunWith(SpringRunner.class)
    @ActiveProfiles("test")
    @SpringBootTest(classes = {TestConfig.DockerEnvironmentVariablesTestConfig.class})
    @TestPropertySource("classpath:docker-service.properties")
    public static class DockerEnvironmentVariablesProviderTest{

        static {
            System.setProperty("frontend.port.8080.tcp.addr", "localhost");
            System.setProperty("frontend.port.8080.tcp.port", "8080");
            System.setProperty("backend.port.8080.tcp.addr", "localhost");
            System.setProperty("backend.port.8080.tcp.port", "8081");
        }

        @Autowired
        @Qualifier("dockerEnvironmentVariablesPathHolderProvider")
        ConnectServiceProvider connectServiceProvider;

        @Test
        public void dockerEnvironmentVariablesPathProviderTest(){
            String frontendPath = connectServiceProvider.getAuthority("frontend");
            String backendPath = connectServiceProvider.getAuthority("backend");
            log.info("DockerEnvironmentVariablesPathProvider : " + frontendPath);
            log.info("DockerEnvironmentVariablesPathProvider : " + backendPath);

            assertThat(frontendPath, is("localhost:8080"));
            assertThat(backendPath, is("localhost:8081"));
        }

    }


}
