package org.debugroom.sample.aws.ecs.backend.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.debugroom.sample.aws.ecs.backend.domain.entity.User;
import org.debugroom.sample.aws.ecs.backend.domain.repository.UserRepository;

@Service
public class SampleServiceImpl implements SampleService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return null;
    }
}
