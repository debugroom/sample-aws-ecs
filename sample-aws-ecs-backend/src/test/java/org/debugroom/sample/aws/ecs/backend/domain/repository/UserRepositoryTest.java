package org.debugroom.sample.aws.ecs.backend.domain.repository;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import org.debugroom.sample.aws.ecs.backend.config.TestConfig;
import org.debugroom.sample.aws.ecs.backend.domain.entity.User;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
//@ContextConfiguration(classes = TestConfig.class)
public class UserRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    public void testFindAll() throws Exception{
        testEntityManager.persist(User.builder().userId("1").userName("Test").build());
        List<User> users = userRepository.findAll();
        users.forEach(e -> log.info(e.getUserName()));
    }

}
