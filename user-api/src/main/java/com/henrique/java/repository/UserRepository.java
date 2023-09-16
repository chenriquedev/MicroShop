package com.henrique.java.repository;

import com.henrique.java.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByCpfAndKey(String cpf, String Key);
    List<User> queryByNomeLike(String nome);
}
