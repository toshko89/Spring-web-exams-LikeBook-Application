package com.example.likebook.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "moods_id")
    private Mood mood;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User creator;

    @Column
    private int likes;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> userLikes;


    public Post() {
    }

    public long getId() {
        return id;
    }

    public Post setId(long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Post setContent(String content) {
        this.content = content;
        return this;
    }

    public Mood getMood() {
        return mood;
    }

    public Post setMood(Mood mood) {
        this.mood = mood;
        return this;
    }

    public User getUser() {
        return creator;
    }

    public Post setUser(User creator) {
        this.creator = creator;
        return this;
    }

    public int getLikes() {
        return likes;
    }

    public Post setLikes(int likes) {
        this.likes = likes;
        return this;
    }

    public Set<User> getUserLikes() {
        return userLikes;
    }

    public Post setUserLikes(Set<User> userLikes) {
        this.userLikes = userLikes;
        return this;
    }
}
