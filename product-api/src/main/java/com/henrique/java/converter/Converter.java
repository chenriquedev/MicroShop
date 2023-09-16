package com.henrique.java.converter;


import com.henrique.java.entity.Category;
import com.henrique.java.entity.Product;
import org.henrique.java.backend.DTO.CategoryDTO;
import org.henrique.java.backend.DTO.ProductDTO;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
public class Converter {

    private final ModelMapper modelMapper = new ModelMapper();

    public Product convertToProduct(ProductDTO dto){
       Product product = modelMapper.map(dto , Product.class);

        if (dto.getCategory() != null) {
            Category category = convertToCategory(dto.getCategory());
            product.setCategory(category);
        }
        return product;
    }

    public ProductDTO convertToProductDTO(Product product){
        ProductDTO dto = modelMapper.map(product , ProductDTO.class);

        if (product.getCategory() != null) {
            CategoryDTO category = convertToCategoryDTO(product.getCategory());
            dto.setCategory(category);
        }
        return dto;
    }

    public Category convertToCategory(CategoryDTO dto){
        return modelMapper.map(dto, Category.class);
    }

    public CategoryDTO convertToCategoryDTO(Category category){
        return modelMapper.map(category, CategoryDTO.class);
    }

}
