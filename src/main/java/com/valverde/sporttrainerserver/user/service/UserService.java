package com.valverde.sporttrainerserver.user.service;

import com.valverde.sporttrainerserver.user.entity.User;
import com.valverde.sporttrainerserver.user.entity.UserTrainingData;
import com.valverde.sporttrainerserver.user.enums.Country;
import com.valverde.sporttrainerserver.user.enums.Gender;
import com.valverde.sporttrainerserver.user.enums.MeterUnits;
import com.valverde.sporttrainerserver.user.repository.UserRepository;
import com.valverde.sporttrainerserver.base.util.EncodingUtils;
import com.valverde.sporttrainerserver.user.dto.RegisterDTO;
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
        user.setUserTrainingData(createDefaultTrainingData(user));
        userRepository.save(user);
    }

    private UserTrainingData createDefaultTrainingData(final User user) {
        final UserTrainingData userTrainingData = new UserTrainingData();
        userTrainingData.setDisplayName(user.getUsername());
        userTrainingData.setMeterUnits(MeterUnits.METRIC);
        userTrainingData.setCountry(Country.POLAND);
        userTrainingData.setGender(Gender.MALE);
        userTrainingData.setUser(user);
        return userTrainingData;
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