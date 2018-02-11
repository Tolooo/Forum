package com.example.Forum.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    private long id;

    private String title;

    private String description;

    @OneToMany
    @JoinTable(name = "CategorySubCategories", joinColumns = {
            @JoinColumn(name = "category_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "sub_categories_id",
                    nullable = false, updatable = false)})
    private List<Category> subCategories;

    @OneToMany
    @JoinTable(name = "CategoryTopics", joinColumns = {
            @JoinColumn(name = "category_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "topics_id",
                    nullable = false, updatable = false)})
    private List<Topic> topics;

    public Category() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
