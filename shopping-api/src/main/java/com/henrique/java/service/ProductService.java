package com.henrique.java.service;

import lombok.extern.slf4j.Slf4j;
import org.henrique.java.backend.DTO.ProductDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ProductService {

    @Value("${PRODUCT_API_URL:http://localhost:8081/product-api/}")
    private String productApiURL;
    public ProductDTO getProductByIdentifier(
            String productIdentifier) {

        log.info(productIdentifier);
        RestTemplate restTemplate = new RestTemplate();
        String url = productApiURL +"products/" + productIdentifier;
        ResponseEntity<ProductDTO> response =
                restTemplate.getForEntity(url, ProductDTO.class);
        log.info(response.getBody().toString());
        return response.getBody();
}
}
