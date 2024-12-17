package com.pia.itacademy.BlackJack.mysql.service;

import com.pia.itacademy.BlackJack.exception.UserAlreadyExistsException;
import com.pia.itacademy.BlackJack.exception.UserNotFoundException;
import com.pia.itacademy.BlackJack.mysql.model.User;
import com.pia.itacademy.BlackJack.mysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        if (userRepository.existsByName(user.getName())) throw new UserAlreadyExistsException("User name already exists in the ddbb");
        return userRepository.save(user);
    }

    @Override
    public User updateUserName(Integer id, String name) throws UserNotFoundException {
        Optional<User> userOp = userRepository.findById(id);
        if (userOp.isEmpty()) throw new UserNotFoundException("User does not exist for id");

        User userdb = userOp.get();
        if(Objects.nonNull(name) && !"".equalsIgnoreCase(name)) {
            userdb.setName(name);
        }

        return userRepository.save(userdb);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Integer id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException("User does not exist for id");
        return user.get();
    }

    @Override
    public User getUserByName(String name) throws UserNotFoundException {
        if (userRepository.existsByName(name)) throw new UserNotFoundException("User name not found in the ddbb");
        return userRepository.findByName(name);
    }
}
