package com.example.onlineshop.Service;

import com.example.onlineshop.Models.Entity.Cart;
import com.example.onlineshop.Models.Entity.Orders;
import com.example.onlineshop.Repository.CartRepository;
import com.example.onlineshop.Repository.OrderRepository;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;

    public void createOrder(Orders order) {
        orderRepository.save(order);
    }

    public Orders findOrdersById(Integer order_id){
        return orderRepository.findOrdersById(order_id);
    }
}
