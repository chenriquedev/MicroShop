package com.henrique.java.service;

import lombok.extern.slf4j.Slf4j;
import org.henrique.java.backend.DTO.ProductDTO;
import org.henrique.java.backend.Exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ProductService {

    @Value("${PRODUCT_API_URL:http://localhost:8081/product-api/}")
    private String productApiURL;
    public ProductDTO getProductByIdentifier(
            String productIdentifier) {

        try{
            RestTemplate restTemplate = new RestTemplate();
            String url = productApiURL +"products/" + productIdentifier;
            ResponseEntity<ProductDTO> response =
                    restTemplate.getForEntity(url, ProductDTO.class);
            if(response.getStatusCode().is2xxSuccessful()){
                return response.getBody();
            }
        }catch (HttpClientErrorException e){
            throw new ProductNotFoundException("Products Not Found");
        }
        return null;
    }
}
