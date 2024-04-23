package com.example.onlineshop.Service;

import com.example.onlineshop.Forms.ProductsAddForm;
import com.example.onlineshop.Models.Entity.Products;
import com.example.onlineshop.Repository.ProductsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.Optional;

@Service
public class ProductsService {
    private static ProductsRepository productsRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository){
        this.productsRepository = productsRepository;
    }

    public Products findProductByName(String name){
        return productsRepository.findProductByName(name);
    }

    public Products findProductById(Integer Id){
        return productsRepository.findProductById(Id);
    }
    @Transactional
    public static void addProduct(ProductsAddForm productsAddForm){

        MultipartFile image = productsAddForm.getImage();
        Products product = new Products();

        try{
            byte[] imageData = image.getBytes();
            product.setName(productsAddForm.getName());
            product.setDescription(productsAddForm.getDescription());
            product.setPrice(productsAddForm.getPrice());
            product.setAvailable_quantity(productsAddForm.getAvailable_quantity());
            product.setCategory(productsAddForm.getCategory());
            product.setImageName(productsAddForm.getName());
            product.setImageData(imageData);

            productsRepository.save(product);

        } catch (IOException e) {
            throw new RuntimeException(e);

        }


    }

    public static String convertImageDataToBase64(byte[] imageData) {
        return Base64.getEncoder().encodeToString(imageData);
    }

    public void deleteProductById(Integer id) {
        productsRepository.deleteById(id);
    }

    public void applyDiscount(Integer id, BigDecimal discountedPrice) {
        Optional<Products> optionalProduct = productsRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Products product = optionalProduct.get();
            product.setPrice(discountedPrice);
            productsRepository.save(product);

        } else {
            throw new IllegalArgumentException("Product with ID " + id + " not found");
        }
    }

    public void editProduct(Integer id, String name, String description){
        Optional<Products> optionalProducts = productsRepository.findById(id);

        if(optionalProducts.isPresent()){
            Products products = optionalProducts.get();
            products.setName(name);
            products.setDescription(description);
            productsRepository.save(products);
        }else {
            throw new IllegalArgumentException("Error");
        }

    }


}
