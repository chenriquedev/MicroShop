package com.henrique.java.converter;

import com.henrique.java.entity.User;
import org.henrique.java.backend.DTO.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {

    private final ModelMapper modelmapper = new ModelMapper();

    public UserDTO convertToDTO(User user){
        return modelmapper.map(user, UserDTO.class);
    }

    public User convertToUser(UserDTO dto){
        return modelmapper.map(dto, User.class);
    }
}
