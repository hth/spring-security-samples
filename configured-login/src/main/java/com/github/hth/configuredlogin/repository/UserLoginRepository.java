package com.github.hth.configuredlogin.repository;

import com.github.hth.configuredlogin.entity.UserLogin;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserLoginRepository extends ReactiveMongoRepository<UserLogin, Id> {
    Mono<UserLogin> findByMail(String mail);
}
