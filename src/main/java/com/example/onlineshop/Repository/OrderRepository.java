package com.example.onlineshop.Repository;

import com.example.onlineshop.Models.Entity.Orders;
import com.example.onlineshop.Models.Entity.User;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

    @Query("SELECT o FROM Orders o WHERE o.user = :user")
    Orders findOrdersByUser(User user);
    @Query("SELECT o FROM Orders o WHERE o.order_id = :order_id")
    Orders findOrdersById(Integer order_id);

}
