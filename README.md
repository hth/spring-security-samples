# spring-security-samples

Using `Spring Boot 3.2.4`

### default-login

Spring security login with default setup. This code is without any bells and whistles. Default configuration. Validate/Authenticate login.

    port: 8091

### configured-login

Populates users in MongoDB. Launch login screen. Validated users and then redirects user 
to the authenticated home page. 

    port: 8092

URL
    
    http://localhost:8092/home/landing

Credentials

    user@hth.github.com
    password

### configured-login-jwt

Everything `configured-login` does plus `jwt`.
**In progress....**

    port: 8093

### Referred Spring Docs

- https://docs.spring.io/spring-security/reference/reactive/configuration/webflux.html#_minimal_webflux_security_configuration
- https://docs.spring.io/spring-security/reference/reactive/configuration/webflux.html
- https://docs.spring.io/spring-security/reference/reactive/authorization/authorize-http-requests.html 