package com.example.Forum.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserCredentials {
    @Id
    private long id;
    @Email(message = "{user.email.email}")
    @Length(max = 30, message = "{user.email.length}")
    private String email;
    @Length(min = 6, max = 30, message = "{user.password.length}")
    private String password;
    private boolean enabled;
}
