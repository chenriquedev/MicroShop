package com.henrique.java.service;

import com.henrique.java.converter.Converter;
import com.henrique.java.entity.Product;
import com.henrique.java.repository.CategoryRepository;
import com.henrique.java.repository.ProductRepository;
import org.henrique.java.backend.DTO.ProductDTO;
import org.henrique.java.backend.Exception.CategoryNotFoundException;
import org.henrique.java.backend.Exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final Converter converter;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, Converter converter) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.converter = converter;
    }

    public List<ProductDTO> getAll(){
        List<Product> product = productRepository.findAll();
        return product.stream().map(converter::convertToProductDTO).toList();
    }

    public List<ProductDTO> getProductByCategoryId(Long categoryId){
        List<Product> product = productRepository.getProductByCategory(categoryId);
        return product.stream().map(converter::convertToProductDTO).toList();
    }

    public ProductDTO findByProductIdentifier(String productIdentifier){
        Product products = productRepository.findByProductIdentifier(productIdentifier);
        if(products != null){
            return converter.convertToProductDTO(products);
        }
        throw new ProductNotFoundException("Product not found");
    }

    public ProductDTO save(ProductDTO dto){
        boolean existsCategory = categoryRepository.existsById(dto.getCategory().getId());
        if(!existsCategory){
            throw new CategoryNotFoundException("Category not found");
        }
        Product productToSave = converter.convertToProduct(dto);
        Product saveProduct = productRepository.save(productToSave);
        return converter.convertToProductDTO(saveProduct);
    }

    public boolean delete(Long productId){
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()){
            productRepository.delete(product.get());
            return true;
        }
        throw new ProductNotFoundException("Product not found");
    }
}
