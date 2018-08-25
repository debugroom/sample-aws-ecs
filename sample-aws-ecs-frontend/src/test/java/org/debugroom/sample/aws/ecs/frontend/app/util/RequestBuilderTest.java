package org.debugroom.sample.aws.ecs.frontend.app.util;

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
public class RequestBuilderTest {

    @RunWith(SpringRunner.class)
    @ActiveProfiles("test")
    @TestPropertySource("classpath:local-service.properties")
    @SpringBootTest(classes = {TestConfig.PropertyPathTestConfig.class})
    public static class PropertyPathRequestBuilderTest{

        @Autowired
        RequestBuilder requestBuilder;

        @Test
        public void buildUriComponentsTest(){
            String uri = requestBuilder.buildUriComponents(
                    "backend", "api/v1/users").toUriString();
            log.info("buildUriComponentsTest : " + uri);
            assertThat(uri, is("http://localhost:8081/backend/api/v1/users"));
        }

    }

    @RunWith(SpringRunner.class)
    @ActiveProfiles("test")
    @TestPropertySource("classpath:ecs-service.properties")
    @SpringBootTest(classes = {TestConfig.EcsPathTestConfig.class})
    public static class EcsPathRequestBuilderTest{

        @Autowired
        RequestBuilder requestBuilder;

        @Test
        public void buildUriComponentsTest(){
            String uri = requestBuilder.buildUriComponents(
                    "backend", "api/v1/users").toUriString();
            log.info("buildUriComponentsTest : " + uri);
            assertThat(uri, is("http://sample-ecs-alb-1379155413.ap-northeast-1.elb.amazonaws.com/backend/api/v1/users"));
        }

    }

    @RunWith(SpringRunner.class)
    @ActiveProfiles("test")
    @TestPropertySource("classpath:docker-service.properties")
    @SpringBootTest(classes = {TestConfig.DockerEnvironmentVariablesTestConfig.class})
    public static class DockerEnvironmentVariablesRequestBuilderTest{

        static {
            System.setProperty("frontend.port.8080.tcp.addr", "localhost");
            System.setProperty("frontend.port.8080.tcp.port", "8080");
            System.setProperty("backend.port.8080.tcp.addr", "localhost");
            System.setProperty("backend.port.8080.tcp.port", "8081");
        }

        @Autowired
        RequestBuilder requestBuilder;

        @Test
        public void buildUriComponentsTest(){
            String uri = requestBuilder.buildUriComponents(
                    "backend", "api/v1/users").toUriString();
            log.info("buildUriComponentsTest : " + uri);
            assertThat(uri, is("http://localhost:8081/backend/api/v1/users"));
        }
    }

    @RunWith(SpringRunner.class)
    @ActiveProfiles("test")
    @TestPropertySource(locations = "classpath:ecs-service.properties",
        properties = {"util.protocol=https"})
    @SpringBootTest(classes = {TestConfig.EcsPathTestConfig.class})
    public static class EcsRequestBuilderTest{

        @Autowired
        RequestBuilder requestBuilder;

        @Test
        public void buildUriComponentsTest(){
            String uri = requestBuilder.buildUriComponents(
                    "backend", "api/v1/users").toUriString();
            log.info("buildUriComponentsTest : " + uri);
            assertThat(uri, is("https://sample-ecs-alb-1379155413.ap-northeast-1.elb.amazonaws.com/backend/api/v1/users"));
        }
    }

}
