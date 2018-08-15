package org.debugroom.sample.aws.ecs.backend.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import org.debugroom.sample.aws.ecs.backend.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
}
