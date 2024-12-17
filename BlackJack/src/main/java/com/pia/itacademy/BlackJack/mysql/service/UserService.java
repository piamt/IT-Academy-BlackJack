package com.pia.itacademy.BlackJack.mysql.service;

import com.pia.itacademy.BlackJack.exception.UserAlreadyExistsException;
import com.pia.itacademy.BlackJack.exception.UserNotFoundException;
import com.pia.itacademy.BlackJack.mysql.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User saveUser(User user) throws UserAlreadyExistsException;
    User updateUserName(Integer id, String name) throws UserNotFoundException;
    void deleteUser(Integer id);
    User getUserById(Integer id) throws UserNotFoundException;
    User getUserByName(String name) throws UserNotFoundException;
}
