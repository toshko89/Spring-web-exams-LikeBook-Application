package com.example.likebook.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "creator")
    private Set<Post> posts;

    @ManyToMany(mappedBy = "userLikes",fetch = FetchType.EAGER)
    private Set<Post> likedPosts;

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public User setPosts(Set<Post> posts) {
        this.posts = posts;
        return this;
    }

    public Set<Post> getLikedPosts() {
        return likedPosts;
    }

    public User setLikedPosts(Set<Post> likedPosts) {
        this.likedPosts = likedPosts;
        return this;
    }
}
