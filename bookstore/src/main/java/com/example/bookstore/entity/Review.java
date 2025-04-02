package com.example.bookstore.entity;

import jakarta.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username; 
    private int rating; 

    @ManyToOne
    private Book book; 

    public Review() {}

    public Review(String username, int rating, Book book) {
        this.username = username;
        this.rating = rating;
        this.book = book;
    }

 
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
}
