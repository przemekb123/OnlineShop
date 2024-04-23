package com.example.onlineshop.Repository;

import com.example.onlineshop.Models.Entity.Cart;
import com.example.onlineshop.Models.Entity.Orders;
import com.example.onlineshop.Models.Entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer > {

    @Query("SELECT u FROM Cart u WHERE u.cart_id = :cart_id")
    Cart findCartById(Integer cart_id);

    Cart findCartByUser(User user);
    @Query("SELECT u FROM Cart u where u.order = :order")
    List<Cart> findCartByOrder(Orders order);
}
