package org.debugroom.sample.aws.ecs.backend.domain.service;

import java.util.List;

import org.debugroom.sample.aws.ecs.backend.domain.entity.User;

public interface SampleService {

    public List<User> getUsers();

}
