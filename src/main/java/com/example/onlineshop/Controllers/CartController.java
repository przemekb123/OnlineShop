package com.example.onlineshop.Controllers;

import com.example.onlineshop.Models.Entity.Cart;
import com.example.onlineshop.Models.Entity.Orders;
import com.example.onlineshop.Models.Entity.User;
import com.example.onlineshop.Repository.CartRepository;
import com.example.onlineshop.Repository.ProductsRepository;
import com.example.onlineshop.Repository.UserRepository;
import com.example.onlineshop.Service.OrderService;
import com.example.onlineshop.Service.ProductsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Controller
public class CartController {

    @Autowired
    ProductsService productsService;
    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/addToCart")
    public String addToCart(@RequestBody Map<String, Integer> productsMap,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {


        String userName = (String) session.getAttribute("username");

        User loggedInUser = userRepository.findByUsername(userName);
/*
        if (loggedInUser == null) {
            return "redirect:/login";
        }

 */


        Orders order = new Orders();
        order.setUser(loggedInUser);
        order.setOrder_date(LocalDateTime.now());
        order.setStatus("Pending");
        orderService.createOrder(order);

        Integer OrderId = order.getOrder_id();

        for (Map.Entry<String, Integer> entry : productsMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();

            Cart cart = new Cart();
            cart.setUser(loggedInUser);
            cart.setProduct(productsRepository.findProductByName(key));
            cart.setOrder(order);
            cart.setQuantity(value);
            cartRepository.save(cart);
        }

        session.setAttribute("OrderId",OrderId);


        return "redirect:/checkout";

    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {

        Integer OrderId = (Integer) session.getAttribute("OrderId");

            Orders order = orderService.findOrdersById(OrderId);
            List<Cart> cartItems = cartRepository.findCartByOrder(order);

            model.addAttribute("cartItems", cartItems);


        return "checkout";
    }

    @PostMapping("/checkout")
    private String realizeCheckout(HttpSession session){
        Integer OrderId = (Integer) session.getAttribute("OrderId");

        Orders order = orderService.findOrdersById(OrderId);

        /*
        order.setStatus("Paied");
        order.setDelivery_address();
        order.setTotal_price();

         */


        return "realizeOrder";

    }


}
