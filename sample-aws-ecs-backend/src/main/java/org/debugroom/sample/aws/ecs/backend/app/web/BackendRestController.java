package org.debugroom.sample.aws.ecs.backend.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.debugroom.sample.aws.ecs.backend.domain.service.SampleService;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class BackendRestController {

    @Autowired
    SampleService sampleService;

    @RequestMapping(value="users", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<?> getUsers(){
        return sampleService.getUsers();
    }

}
