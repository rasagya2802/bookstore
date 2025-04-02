package com.example.bookstore.entity;

import jakarta.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; 
    private String text; 

    @ManyToOne
    private Book book;

    public Comment() {}

    public Comment(String username, String text, Book book) {
        this.username = username;
        this.text = text;
        this.book = book;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
}

