package com.example.onlineshop.Controllers;

import com.example.onlineshop.Models.Entity.Cart;
import com.example.onlineshop.Models.Entity.User;
import com.example.onlineshop.Repository.CartRepository;
import com.example.onlineshop.Repository.ProductsRepository;
import com.example.onlineshop.Repository.UserRepository;
import com.example.onlineshop.Service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderController {

    //TODO: dodawanie i usuwanie koszyka jedynie na widkoku w javascript, a po kliknieciu
    //finalizacji przenoszenie do kontrolera przez GET/POST t(tworzenie zamowienia w bazie danych)

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private OrderService orderService;





}
