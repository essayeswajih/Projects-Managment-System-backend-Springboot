package com.islem.tasks.controller;

import com.islem.tasks.entity.User;
import com.islem.tasks.service.UserServicee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServicee userServicee;

    public UserController(UserServicee userServicee) {
        this.userServicee = userServicee;
    }

    @GetMapping("/admin/all")
    public ResponseEntity<?> getAllUsers() {

            return new ResponseEntity<>(userServicee.findAll(), HttpStatus.OK);

    }

    @GetMapping("/admin/find/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        User user = userServicee.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/admin/findByEmail/{ide}")
    public ResponseEntity<User> getUserByIde(@PathVariable("ide") String ide) {
        User user = userServicee.findUserByEmail(ide);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/admin/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userServicee.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/admin/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updateUser = userServicee.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
        userServicee.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
