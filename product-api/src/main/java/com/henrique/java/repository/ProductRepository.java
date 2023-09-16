package com.henrique.java.repository;

import com.henrique.java.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select p from product p join category c on p.category.id = c.id where c.id =:categoryId")
    List<Product> getProductByCategory(@Param("categoryId") Long categoryId);

    Product findByProductIdentifier(String productIdentifier);
}