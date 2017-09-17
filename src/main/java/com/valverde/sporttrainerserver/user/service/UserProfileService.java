package com.valverde.sporttrainerserver.user.service;

import com.valverde.sporttrainerserver.user.dto.UserDataDTO;
import com.valverde.sporttrainerserver.user.dto.UserTrainingDataDTO;
import com.valverde.sporttrainerserver.user.entity.User;
import com.valverde.sporttrainerserver.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserProfileService {

    public UserDataDTO findUserData(final String username) {
        final User user = userRepository.findByUsername(username);
        final UserTrainingDataDTO trainingDataDTO = UserTrainingDataDTO.toDTO(user.getUserTrainingData());
        return new UserDataDTO(trainingDataDTO);
    }

    public UserProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;
}