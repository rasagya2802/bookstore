package com.example.bookstore.service;

import com.example.bookstore.entity.*;
import com.example.bookstore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap; 
import java.util.Map; 

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void addToCart(User user, Long bookId, int quantity) {
        Cart cart = user.getCart();
        if (cart == null) {
            throw new IllegalStateException("User has no cart associated");
        }
        cart.addBook(bookId, quantity);
        cartRepository.save(cart);
    }

    @Transactional
public void updateCart(User user, Long bookId, int quantity) {
    Cart cart = user.getCart();
    if (cart == null) {
        throw new IllegalStateException("User has no cart associated");
    }
    if (quantity <= 0) {
        cart.removeBook(bookId);
    } else {
        cart.getBookQuantities().put(bookId, quantity);
    }
    cartRepository.save(cart);
}

    @Transactional
    public void removeFromCart(User user, Long bookId) {
        Cart cart = user.getCart();
        if (cart == null) {
            throw new IllegalStateException("User has no cart associated");
        }
        cart.removeBook(bookId);
        cartRepository.save(cart);
    }

    @Transactional
    public Order placeOrder(User user, String address, String phoneNumber) {
        Cart cart = user.getCart();
        if (cart == null || cart.getBookQuantities().isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }
        Order order = new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setPhoneNumber(phoneNumber);
        order.setBooks(new HashMap<>(cart.getBookQuantities())); 
        orderRepository.save(order);
        cart.getBookQuantities().clear();
        cartRepository.save(cart);
        return order;
    }
}