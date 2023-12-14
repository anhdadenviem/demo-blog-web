package com.example.blogpages.model;

import ch.qos.logback.classic.pattern.LineOfCallerConverter;

import javax.persistence.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String genre;//thể loại
    private LocalDate date;
    private String mode;
    private Long likee;

    @ManyToOne
    User user;

    public Blog() {
    }

    public Blog(Long id, String title, String content, String genre, LocalDate date, String mode, Long likee, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.genre = genre;
        this.date = date;
        this.mode = mode;
        this.likee = likee;
        this.user = user;
    }

    public Long getLikee() {
        return likee;
    }

    public void setLikee(Long likee) {
        this.likee = likee;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
