package org.debugroom.sample.aws.ecs.frontend.app.web;

import org.debugroom.sample.aws.ecs.frontend.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Controller
public class FrontendController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(method = RequestMethod.GET,  value="hello")
    public String hello(Model model){
        model.addAttribute("users",
                Arrays.asList(User.builder()
                        .userId("1")
                        .userName("Test")
                        .build()));
        return "hello";
    }

}
