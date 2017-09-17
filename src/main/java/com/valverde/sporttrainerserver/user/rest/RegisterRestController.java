package com.valverde.sporttrainerserver.user.rest;

import com.valverde.sporttrainerserver.user.service.UserService;
import com.valverde.sporttrainerserver.user.dto.RegisterDTO;
import com.valverde.sporttrainerserver.user.dto.RegisterValidationDTO;
import com.valverde.sporttrainerserver.user.service.RegisterValidationService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CommonsLog
@CrossOrigin
@RestController
public class RegisterRestController {

    @PostMapping("/register")
    public ResponseEntity<List<RegisterValidationDTO>> register(@RequestBody RegisterDTO register) {
        try {
            List<RegisterValidationDTO> validationErrors = registerValidationService.validate(register);
            if (validationErrors.isEmpty()) {
                userService.register(register);
                log.info("User with username: "+register.getUsername()+" registered.");
                return new ResponseEntity<>(HttpStatus.OK);
            }
            log.info("User with username: "+register.getUsername()+" has validation errors.");
            return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Problem while user", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public RegisterRestController(UserService userService, RegisterValidationService registerValidationService) {
        this.userService = userService;
        this.registerValidationService = registerValidationService;
    }

    private final UserService userService;

    private final RegisterValidationService registerValidationService;
}
