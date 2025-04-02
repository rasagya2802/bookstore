package com.example.bookstore.entity;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<Long, Integer> bookQuantities = new HashMap<>();
 

    public Cart() {}

    public Map<Long, Integer> getBookQuantities() {
        return bookQuantities;
    }

    public void addBook(Long bookId, int quantity) {
        bookQuantities.put(bookId, bookQuantities.getOrDefault(bookId, 0) + quantity);
    }

    public void removeBook(Long bookId) {
        bookQuantities.remove(bookId);
    }
}
