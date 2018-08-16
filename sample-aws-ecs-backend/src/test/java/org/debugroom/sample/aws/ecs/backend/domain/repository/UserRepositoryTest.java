package org.debugroom.sample.aws.ecs.backend.domain.repository;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import org.debugroom.sample.aws.ecs.backend.domain.entity.User;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    UserRepository userRepository;

    @Before
    public void before(){
        testEntityManager.persist(User.builder().userId("1").userName("Test1").build());
        testEntityManager.persist(User.builder().userId("2").userName("Test2").build());
        testEntityManager.persist(User.builder().userId("3").userName("Test3").build());
    }

    @Test
    public void testFindAll() throws Exception{
        List<User> users = userRepository.findAll();
        users.forEach(e -> {
            log.info(e.getUserName());
            switch (e.getUserId()) {
                case "1": assertThat(e.getUserName() , is("Test1")); break;
                case "2": assertThat(e.getUserName() , is("Test2")); break;
                case "3": assertThat(e.getUserName() , is("Test3")); break;
            }
        });
    }

}
