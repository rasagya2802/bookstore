package com.example.bookstore.controller;

import com.example.bookstore.entity.*;

import com.example.bookstore.repository.*;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CommentRepository commentRepository;

        
    @GetMapping("/books")
    public String listBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books); 
        return "books"; 
    }
   
        @GetMapping("/book-details/{id}")
        public String bookDetails(@PathVariable Long id, Model model) {
            Book book = bookRepository.findById(id).orElse(null);
            List<Review> reviews = reviewRepository.findByBookId(id);
            List<Comment> comments = commentRepository.findByBookId(id);
    
            model.addAttribute("book", book);
            model.addAttribute("reviews", reviews);
            model.addAttribute("comments", comments);
            
    
            return "book-details";
        }
    
        @PostMapping("/book-details/{id}/add-review")
public String addReview(@PathVariable Long id, @RequestParam int rating, HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (user == null) {
        return "redirect:/user/login";
    }
    String username = user.getUsername(); 
    Book book = bookRepository.findById(id).orElse(null);
    if (book != null) {
        Review review = new Review(username, rating, book);
        reviewRepository.save(review);
    }
    return "redirect:/book-details/" + id;
}

@PostMapping("/book-details/{id}/add-comment")
public String addComment(@PathVariable Long id, @RequestParam String text, HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (user == null) {
        return "redirect:/user/login";
    }
    String username = user.getUsername(); 
    Book book = bookRepository.findById(id).orElse(null);
    if (book != null) {
        Comment comment = new Comment(username, text, book);
        commentRepository.save(comment);
    }
    return "redirect:/book-details/" + id;
}

    }