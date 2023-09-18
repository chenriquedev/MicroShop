package com.henrique.java.service;

import com.henrique.java.converter.UserConverter;
import com.henrique.java.entity.User;
import com.henrique.java.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.henrique.java.backend.DTO.UserDTO;
import org.henrique.java.backend.Exception.UserErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public List<UserDTO> getAll(){
        List<User> users = userRepository.findAll();
        return users.stream().map(userConverter::convertToDTO).toList();
    }

    public UserDTO findById(Long userId){
        Optional<User> user = userRepository.findById(userId);
        return user.map(userConverter::convertToDTO).orElseThrow(() -> new UserErrorException("User Not Found"));
    }

    public UserDTO saveUser(UserDTO dto){

        dto.setKey(UUID.randomUUID().toString());
        if(validate(dto)){
            User userToSave = userConverter.convertToUser(dto);
            userToSave.setDataCadastro(LocalDate.now());
            User saveUser = userRepository.save(userToSave);
            return userConverter.convertToDTO(saveUser);
        }
        throw new UserErrorException("Could not create user, please, verify the fields and try again");
    }

    public boolean delete(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.delete(user.get());
            return true;
        }
        throw new UserErrorException("User Not Found");
    }

    public UserDTO findByCpf(String cpf, String key){
        User user = userRepository.findByCpfAndKey(cpf, key);
        if(user != null){
            return userConverter.convertToDTO(user);
        }
        throw new UserErrorException("User Not Found");
    }

    public List<UserDTO> queryByName(String name){
        User user = new User();
        user.setNome(name);
        Example<User> example = Example.of(user, ExampleMatcher.matching()
                .withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        List<User> users = userRepository.findAll(example);

        return users.stream().map(userConverter::convertToDTO).toList();
    }

    public boolean validate(UserDTO dto){

        if (dto.getNome() != null && !dto.getNome().isEmpty() &&
                dto.getCpf() != null && !dto.getCpf().isEmpty() &&
                dto.getEndereco() != null && !dto.getEndereco().isEmpty() &&
                dto.getEmail() != null && !dto.getEmail().isEmpty() &&
                dto.getTelefone() != null && !dto.getTelefone().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

}
