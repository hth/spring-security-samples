package com.github.hth.configuredlogin.runner;

import com.github.hth.configuredlogin.entity.UserLogin;
import com.github.hth.configuredlogin.repository.UserLoginRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PopulateUserRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(PopulateUserRunner.class);

    @Value("${domain}")
    private String domain;

    private final UserLoginRepository userLoginRepository;
    private final Faker faker;

    public PopulateUserRunner(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
        this.faker = new Faker();
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Populating user login");
        Mono<Void> deleteAllRecordMono = userLoginRepository.deleteAll();
        Mono<UserLogin> first = Mono.just(new UserLogin(1, "user@" + domain, "password"))
                .flatMap(userLoginRepository::insert)
                .doOnNext(userLogin -> log.info("Added user id={} {} mail={}", userLogin.getId(), "password", userLogin.getMail()));
        Flux<UserLogin> createUserFlux = Flux.range(2, 5)
                .map(i -> new UserLogin(i, generateMail(), generateRandomPassword(i)))
                .flatMap(userLoginRepository::insert)
                .doOnNext(userLogin -> log.info("Added user id={} {} mail={}", userLogin.getId(), generateRandomPassword(userLogin.getId()), userLogin.getMail()));
        Flux.concat(deleteAllRecordMono, first, createUserFlux).subscribe();
    }

    private String generateRandomPassword(int id) {
        return "password" + id;
    }

    private String generateMail() {
        return faker.name().firstName().toLowerCase() + "@" + domain;
    }
}
