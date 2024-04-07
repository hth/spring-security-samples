package com.github.hth.configuredlogin.security;

import com.github.hth.configuredlogin.entity.UserLogin;
import com.github.hth.configuredlogin.repository.UserLoginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
public class DefinedUserDetailsService implements ReactiveUserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(DefinedUserDetailsService.class);

    private final UserLoginRepository userLoginRepository;

    public DefinedUserDetailsService(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }

    @Override
    public Mono<UserDetails> findByUsername(String mail) {
        log.info("Lookup user {}", mail);
        return userLoginRepository.findByMail(mail)
                .map(this::getUser);
    }

    private User getUser(UserLogin userLogin) {
        return new User(
                userLogin.getMail(),
                userLogin.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
