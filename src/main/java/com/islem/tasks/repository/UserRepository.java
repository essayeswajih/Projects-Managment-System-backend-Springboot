package com.islem.tasks.repository;

import com.islem.tasks.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findUserByEmailAndPassword(String email, String password);
    void deleteUserById (Integer id);
    Optional<User> findByEmail(String email);

}

