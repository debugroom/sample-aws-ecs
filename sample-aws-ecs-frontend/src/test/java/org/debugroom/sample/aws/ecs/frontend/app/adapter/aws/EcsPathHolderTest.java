package org.debugroom.sample.aws.ecs.frontend.app.adapter.aws;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import lombok.extern.slf4j.Slf4j;
import org.debugroom.sample.aws.ecs.frontend.app.adapter.ServicePathHolder;
import org.debugroom.sample.aws.ecs.frontend.config.TestConfig;
import org.debugroom.sample.aws.ecs.frontend.config.WebApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:ecs-service.properties")
@ActiveProfiles("test")
@SpringBootTest(classes = {TestConfig.EcsPathTestConfig.class})
public class EcsPathHolderTest {

    @Autowired
    @Qualifier("ecsPathHolder")
    ServicePathHolder servicePathHolder;

    @Test
    public void testGetHosts(){
        Map<String, List<String>> hosts = servicePathHolder.getHosts();

        if(hosts.size() == 0){fail();}
        hosts.forEach((s, l) -> {
            log.info(s + " : " + hosts.get(s).toString());
            switch (s){
                case "frontend" : assertThat(l, hasItem("sample-ecs-alb-1379155413.ap-northeast-1.elb.amazonaws.com")); break;
                case "backend" : assertThat(l, hasItem("sample-ecs-alb-1379155413.ap-northeast-1.elb.amazonaws.com")); break;
            }
        });
    }

}
