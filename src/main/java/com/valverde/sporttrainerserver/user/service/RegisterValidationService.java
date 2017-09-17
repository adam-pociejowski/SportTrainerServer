package com.valverde.sporttrainerserver.user.service;

import com.valverde.sporttrainerserver.user.entity.User;
import com.valverde.sporttrainerserver.user.dto.RegisterDTO;
import com.valverde.sporttrainerserver.user.dto.RegisterValidationDTO;

import static com.valverde.sporttrainerserver.user.enums.ValidationErrorType.PASSWORD;
import static com.valverde.sporttrainerserver.user.enums.ValidationErrorType.USERNAME;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterValidationService {

    public List<RegisterValidationDTO> validate(RegisterDTO registerDTO) {
        List<RegisterValidationDTO> validationErrors = new ArrayList<>();
        validateUsername(registerDTO, validationErrors);
        validatePassword(registerDTO, validationErrors);
        return validationErrors;
    }

    private void validateUsername(RegisterDTO registerDTO, List<RegisterValidationDTO> validationErrors) {
        User user = userService.findByUsernameWithNull(registerDTO.getUsername());
        if (user != null) {
            validationErrors.add(new RegisterValidationDTO(USERNAME, "Username already taken"));
        } if (registerDTO.getUsername().length() < 5) {
            validationErrors.add(new RegisterValidationDTO(USERNAME, "Username must have more than 4 characters"));
        }
    }

    private void validatePassword(RegisterDTO registerDTO, List<RegisterValidationDTO> validationErrors) {
        if (registerDTO.getPassword().length() < 5) {
            validationErrors.add(new RegisterValidationDTO(PASSWORD, "Password must have more than 4 characters"));
        }
    }

    public RegisterValidationService(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;
}
