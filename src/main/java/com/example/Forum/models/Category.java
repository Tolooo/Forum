package com.example.Forum.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    private String title;

    private String description;

    private Date creationDate;

    @OneToOne
    private UserCredentials user;

    @OneToMany
    @JoinTable(name = "CategorySubCategories", joinColumns = {
            @JoinColumn(name = "category_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "sub_categories_id",
                    nullable = false, updatable = false)})
    private List<Category> subCategories;

    @OneToMany(mappedBy ="category")
//    @JoinTable(name = "CategoryTopics", joinColumns = {
//            @JoinColumn(name = "category_id", nullable = false, updatable = false)},
//            inverseJoinColumns = {@JoinColumn(name = "topics_id",
//                    nullable = false, updatable = false)})
    private List<Topic> topics;

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public UserCredentials getUser() {
        return user;
    }

    public void setUser(UserCredentials user) {
        this.user = user;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
