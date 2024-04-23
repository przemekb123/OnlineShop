package com.example.onlineshop.Controllers;

import com.example.onlineshop.Forms.ProductsAddForm;
import com.example.onlineshop.Models.Entity.Products;
import com.example.onlineshop.Repository.ProductsRepository;
import com.example.onlineshop.Service.ProductsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductsController {

    @Autowired
    ProductsService productsService;
    @Autowired
    ProductsRepository productsRepository;
    @GetMapping("/")
    public String showProducts(Model model){

        List<Products> productsList = productsRepository.findAll();

        for (Products product : productsList) {
            byte[] imageData = product.getImageData();
            String base64ImageData = ProductsService.convertImageDataToBase64(imageData);
            product.setBase64ImageData(base64ImageData);
        }

        model.addAttribute("products", productsRepository.findAll());

        return "index";
    }

    @PutMapping("/edit/{id}")
    public String editProducts(){
        return "redirect:/products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productsService.deleteProductById(id);
        return "redirect:/";
    }

    @GetMapping("/addProduct")
    public String addProductShow(Model model){
        ProductsAddForm newProduct = new ProductsAddForm();
        model.addAttribute("ProductsForm", newProduct);

        return "/addProduct";
    }

    @PostMapping("/addProduct")
    public String addProducts(@Valid @ModelAttribute("ProductsForm") ProductsAddForm productsAddForm,
                              BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            System.out.println("error / test");
            return "addProduct";
        }

        ProductsService.addProduct(productsAddForm);
        return "redirect:/";
    }

    @PostMapping("/discount/{id}")
    public String applyDiscount(@PathVariable("id") Integer id, @RequestParam("discountedPrice") BigDecimal discountedPrice) {
        productsService.applyDiscount(id, discountedPrice);
        return "redirect:/";
    }


    @PostMapping("/edit/{productId}")
    public String editProduct(@PathVariable Integer productId, @RequestParam String name, @RequestParam String description) {
        productsService.editProduct(productId, name, description);

        return "redirect:/";


    }




}
