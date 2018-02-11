package com.example.Forum.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Topic {

    @Id
    private long id;

    private String title;

    private String description;

    @OneToMany
    @JoinTable(name = "TopicComments", joinColumns = {
            @JoinColumn(name = "topic_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "comments_id",
                    nullable = false, updatable = false)})
    private List<Comment> comments;

    public Topic() {
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
