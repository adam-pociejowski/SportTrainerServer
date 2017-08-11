package com.valverde.sporttrainerserver.service;

import com.valverde.sporttrainerserver.entity.User;
import com.valverde.sporttrainerserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UserNotFoundException("No user with username: "+username+" found.");
        return user;
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    class UserNotFoundException extends RuntimeException {
        UserNotFoundException(String message) {
            super(message);
        }
    }

    private final UserRepository userRepository;
}