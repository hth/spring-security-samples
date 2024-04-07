package com.github.hth.configuredlogin.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("USER_LOGIN")
@AllArgsConstructor
@Accessors(chain = true)
public class UserLogin {

    @Id
    private int id;

    @Field("MAIL")
    private String mail;

    /** Unsecured password. Please encrypt this password with BCrypt. */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Field("PASSWORD")
    private String password;
}
