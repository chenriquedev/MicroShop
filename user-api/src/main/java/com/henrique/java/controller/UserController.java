package com.henrique.java.controller;

import com.henrique.java.service.UserService;
import lombok.RequiredArgsConstructor;
import org.henrique.java.backend.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-api")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;


    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<UserDTO> user = userService.getAll();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id){
        UserDTO user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok().body(user);
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/user")
    public ResponseEntity<UserDTO> newUser(@RequestBody UserDTO userDto){
        UserDTO user = userService.saveUser(userDto);
        if(user != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userDto);
    }

    @GetMapping("/user/cpf/{cpf}")
    public ResponseEntity<UserDTO> findByCpf(
            @RequestParam(name = "key", required = true) String key,
            @PathVariable("cpf") String cpf){
        UserDTO user = userService.findByCpf(cpf, key);
        if(user != null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable("id") Long id){
        boolean deleted = userService.delete(id);
        if (deleted){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/user/search")
    public ResponseEntity<List<UserDTO>> queryByName(
            @RequestParam(name = "nome") String name
    ){
        if (name.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        List<UserDTO> users = userService.queryByName(name);
        return ResponseEntity.ok(users);
    }

}
