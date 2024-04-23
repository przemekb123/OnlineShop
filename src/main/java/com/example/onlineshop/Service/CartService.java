package com.example.onlineshop.Service;

import com.example.onlineshop.Models.Entity.Cart;
import com.example.onlineshop.Repository.CartRepository;
import com.example.onlineshop.Repository.UserRepository;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    @Autowired
    public CartService(CartRepository cartRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }


    public List<Cart> getCartItemsByOrderId(Integer orderId) {
        return null;
    }







}
