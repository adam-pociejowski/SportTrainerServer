package com.valverde.sporttrainerserver.base.service;

import com.valverde.sporttrainerserver.base.entity.User;
import com.valverde.sporttrainerserver.base.repository.UserRepository;
import com.valverde.sporttrainerserver.base.util.EncodingUtils;
import com.valverde.sporttrainerserver.register.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    public User findByUsername(String username) {
        final User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UserNotFoundException("No user with username: "+username+" found.");
        return user;
    }

    public User findByUsernameWithNull(String username) {
        return userRepository.findByUsername(username);
    }

    public void register(RegisterDTO registerDTO) {
        final User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPasswordHash(EncodingUtils.encodePassword(registerDTO.getPassword()));
        user.setRegisterDate(new Date());
        userRepository.save(user);
    }

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