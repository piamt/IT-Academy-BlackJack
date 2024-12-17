package com.pia.itacademy.BlackJack.controller;

import com.pia.itacademy.BlackJack.exception.UserAlreadyExistsException;
import com.pia.itacademy.BlackJack.exception.UserNotFoundException;
import com.pia.itacademy.BlackJack.mysql.model.User;
import com.pia.itacademy.BlackJack.mysql.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/new")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) throws UserAlreadyExistsException {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PutMapping("/player/{id}")
    public ResponseEntity<User> saveUser(@PathVariable Integer id, @RequestBody String name) throws UserNotFoundException {
        return ResponseEntity.ok(userService.updateUserName(id, name));
    }


}
