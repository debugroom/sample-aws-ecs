package org.debugroom.sample.aws.ecs.frontend.app.web;

import java.util.Arrays;

import org.debugroom.sample.aws.ecs.frontend.app.util.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import org.debugroom.sample.aws.ecs.specification.api.model.User;

@Controller
public class FrontendController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RequestBuilder requestBuilder;

    @RequestMapping(method = RequestMethod.GET,  value="hello")
    public String hello(Model model){

        String serviceName = "backend";

        model.addAttribute("users", restTemplate.getForObject(
              requestBuilder.buildUriComponents(serviceName,
                      "api/v1/users").toUriString(), User[].class));
        return "hello";
    }

}
