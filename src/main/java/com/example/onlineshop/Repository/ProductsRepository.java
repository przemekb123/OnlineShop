package com.example.onlineshop.Repository;

import com.example.onlineshop.Models.Entity.Products;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products, Integer> {
    @Query("Select u from Products u Where u.product_id = :product_id ")
    Products findProductById(Integer product_id);
    @Query("Select u from Products u Where u.name = :name ")
    Products findProductByName(String name);

    @Query("SELECT u from Products u Where u.product_id = :id")
    Products findProductsById(Integer id);
    @Query(value = "SELECT imageData FROM Products")
    List<byte[]> findAllImages();

    @Query("SELECT p.imageData FROM Products p WHERE p.product_id = :productId")
    Optional<byte[]> findImageDataById(@Param("productId") long productId);

    void deleteById(Integer id);

}

