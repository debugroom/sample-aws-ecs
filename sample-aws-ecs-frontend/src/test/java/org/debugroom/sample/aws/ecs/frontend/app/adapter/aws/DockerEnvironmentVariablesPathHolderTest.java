package org.debugroom.sample.aws.ecs.frontend.app.adapter.aws;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.debugroom.sample.aws.ecs.frontend.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

import org.debugroom.sample.aws.ecs.frontend.app.adapter.ServicePathHolder;

@Slf4j
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:docker-service.properties")
@ActiveProfiles("test")
@SpringBootTest(classes = {TestConfig.DockerEnvironmentVariablesTestConfig.class})
public class DockerEnvironmentVariablesPathHolderTest {

    @Autowired
    @Qualifier("dockerEnvironmentVariablesPathHolder")
    ServicePathHolder servicePathHolder;

    static {
        System.setProperty("frontend.port.8080.tcp.addr", "localhost");
        System.setProperty("frontend.port.8080.tcp.port", "8080");
        System.setProperty("backend.port.8080.tcp.addr", "localhost");
        System.setProperty("backend.port.8080.tcp.port", "8081");
    }

    @Test
    public void testGetHosts(){
        Map<String, List<String>> hosts = servicePathHolder.getHosts();

        if(hosts.size() == 0){ fail(); }
        hosts.forEach((s, l) -> {
            log.info(s + " : " + hosts.get(s).toString());
            switch (s){
                case "frontend" : assertThat(l, hasItem("localhost"));
                case "backend" : assertThat(l, hasItem("localhost"));
            }
        });
    }

    @Test
    public void testGetHostPorts(){
        Map<String, String> ports = servicePathHolder.getHostPorts();

        if(ports.size() == 0){ fail(); }
        ports.forEach((s1, s2) -> {
            log.info(s1 + " : " + ports.get(s1).toString());
            switch (s1){
                case "frontend" : assertThat(s2, is("8080")); break;
                case "backend" : assertThat(s2, is("8081")); break;
            }
        });
    }

}
