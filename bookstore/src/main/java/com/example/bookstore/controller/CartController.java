package com.example.bookstore.controller;

import com.example.bookstore.entity.*;
import com.example.bookstore.service.*;
import com.example.bookstore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CartService cartService;

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        Cart cart = user.getCart();
        model.addAttribute("cart", cart);
        model.addAttribute("books", bookRepository.findAllById(cart.getBookQuantities().keySet()));
        return "cart"; 
    }

    @Transactional
    @PostMapping("/add/{bookId}")
    public String addToCart(@PathVariable Long bookId, @RequestParam int quantity, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        cartService.addToCart(user, bookId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/update/{bookId}")
    public String updateCart(@PathVariable Long bookId, @RequestParam int quantity, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        cartService.updateCart(user, bookId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove/{bookId}")
    public String removeFromCart(@PathVariable Long bookId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        cartService.removeFromCart(user, bookId);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        model.addAttribute("user", user);
        return "checkout";
    }

    @PostMapping("/placeOrder")
    public String placeOrder(@RequestParam String address, @RequestParam String phoneNumber, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        Order order = cartService.placeOrder(user, address, phoneNumber);
        model.addAttribute("order", order);
        return "orderConfirmation"; 
    }
}