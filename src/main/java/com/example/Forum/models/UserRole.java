package com.example.Forum.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class UserRole {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Length(min = 6, max = 30, message = "{role.role.length}")
    private String role;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<UserCredentials> users;

    public UserRole() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<UserCredentials> getUsers() {
        return users;
    }

    public void setUsers(List<UserCredentials> users) {
        this.users = users;
    }
}
