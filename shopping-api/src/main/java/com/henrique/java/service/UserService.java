package com.henrique.java.service;

import lombok.extern.slf4j.Slf4j;
import org.henrique.java.backend.DTO.UserDTO;
import org.henrique.java.backend.Exception.UserErrorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Value("${USER_API_URL:http://localhost:8080/user-api/}")
    private String userApiURL;

    public UserDTO getUserByCPF(String cpf, String key){
        try{
            RestTemplate restTemplate = new RestTemplate();
            String url = userApiURL + "user/" + "cpf/" + cpf + "?key=" + key;
            ResponseEntity<UserDTO> response =
                    restTemplate.getForEntity(url, UserDTO.class);
            if(response.getStatusCode().is2xxSuccessful()){
                return response.getBody();
            }
        }catch (HttpClientErrorException e){
            throw new UserErrorException("Cpf or Key Incorrect");
        }
        return null;

    }
}
