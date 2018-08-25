package org.debugroom.sample.aws.ecs.frontend.app.web;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.ResultActions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.debugroom.sample.aws.ecs.frontend.config.WebApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = WebApp.class)
public class FrontendControllerTest {

    @Autowired
    WebApplicationContext context;

    @Autowired
    RestTemplate restTemplate;

    MockMvc mockMvc;

    @Before
    public void setUpMockMvc(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testHello() throws Exception{

        MockRestServiceServer mockServer = MockRestServiceServer.bindTo(restTemplate).build();
        mockServer.expect(requestTo("http://localhost:8081/backend/api/v1/users"))
                .andRespond(withSuccess(
                    "[{\"userId\":\"00000000\", \"userName\":\"(・∀・)\", \"loginId\":\"org.debugroom.test1\",\"ver\":0,\"lastUpdatedDate\":\"2014-12-31T15:00:00.000+0000\"}]",
                        MediaType.APPLICATION_JSON_UTF8));

        MvcResult mvcResult = mockMvc.perform(get("http://localhost:8080/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }


}
