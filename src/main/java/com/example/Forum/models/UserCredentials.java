package com.example.Forum.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class UserCredentials {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column(nullable = false, unique = true)
    @Length(min = 2, max = 30, message = "{user.username.length}")
    private String username;

    @Email(message = "{user.email.email}")
    @Length(max = 30, message = "{user.email.length}")
    private String email;

    @Length(min = 6, max = 30, message = "{user.password.length}")
    private String password;

    private boolean enabled;

    private Date creationDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UserCredentialsRoles", joinColumns = {
            @JoinColumn(name = "user_credentials_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "roles_id",
                    nullable = false, updatable = false)})
    private List<UserRole> roles;

    public UserCredentials() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

}
