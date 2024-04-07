package com.github.hth.configuredlogin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/home")
public class UserHomeController {
    private static final Logger log = LoggerFactory.getLogger(UserHomeController.class);

    @RequestMapping("/landing")
    public ResponseEntity<Mono<String>> home(Mono<Authentication> authenticationMono) {
        log.info("Reached home");
        Mono<String> message = authenticationMono.map(a -> "Hello " + a.getName());
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
