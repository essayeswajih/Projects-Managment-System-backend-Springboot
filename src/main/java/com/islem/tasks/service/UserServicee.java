package com.islem.tasks.service;

import com.islem.tasks.entity.User;
import com.islem.tasks.exception.UserNotFoundException;
import com.islem.tasks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServicee {
    private final UserRepository userRepository;
    @Autowired
    public UserServicee(UserRepository userRepository)
    {
        this.userRepository= userRepository;
    }

    public User addUser(User user) {
        user.setUserCode(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    public User updateUser(User user)
    {
        return userRepository.save(user);
    }

    public User findUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User by id " + email + " was not found"));
    }

    public void deleteUser(Integer id){
        userRepository.deleteUserById(id);
    }
}
