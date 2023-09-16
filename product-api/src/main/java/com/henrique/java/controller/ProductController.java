package com.henrique.java.controller;

import com.henrique.java.service.ProductService;
import jakarta.validation.Valid;
import org.henrique.java.backend.DTO.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;


import java.util.List;

@RestController
@RequestMapping("/product-api")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductDTO>> getProducts(){
        List<ProductDTO> product = productService.getAll();
        return ResponseEntity.ok(product);

    }

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> newProduct(@Valid @RequestBody ProductDTO dto){
        ProductDTO product = productService.save(dto);
        if(product != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @GetMapping("/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getProductByCategory(@PathVariable("categoryId") Long categoryId){
        List<ProductDTO> products = productService.getProductByCategoryId(categoryId);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/{identifier}")
    public ResponseEntity<ProductDTO> getProductByIdentifier(@PathVariable("identifier") String productIdentifier){
        ProductDTO product = productService.findByProductIdentifier(productIdentifier);
        if (product != null){
            return ResponseEntity.ok(product);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<ProductDTO> delete(@PathVariable("id") Long id){
        boolean delete = productService.delete(id);
        if(delete){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

