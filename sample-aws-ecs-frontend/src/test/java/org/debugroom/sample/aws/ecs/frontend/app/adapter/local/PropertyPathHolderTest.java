package org.debugroom.sample.aws.ecs.frontend.app.adapter.local;

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
import org.debugroom.sample.aws.ecs.frontend.config.WebApp;

@Slf4j
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@TestPropertySource("classpath:local-service.properties")
@SpringBootTest(classes = TestConfig.PropertyPathTestConfig.class)
public class PropertyPathHolderTest {

    @Autowired
    @Qualifier("propertyPathHolder")
    ServicePathHolder servicePathHolder;

    @Test
    public void testGetHosts(){
        Map<String, List<String>> hosts = servicePathHolder.getHosts();

        if(hosts.size() == 0){fail();}
        hosts.forEach((s, l) ->{
            log.info(s + " : " + hosts.get(s).toString());
            switch (s){
                case "frontend" : assertThat(l, hasItems("localhost", "192.168.1.3")); break;
                case "backend" : assertThat(l, hasItems("localhost")); break;
            }
        });

    }

    @Test
    public void testGetHostPorts(){
        Map<String, String> ports = servicePathHolder.getHostPorts();

        if(ports.size() == 0){fail();}
        ports.forEach((s1, s2) -> {
            log.info(s1 + " : " + ports.get(s1).toString());
            switch (s1){
                case "frontend" : assertThat(s2, is("8080")); break;
                case "backend" : assertThat(s2, is("8081")); break;
            }
        });

    }
}
