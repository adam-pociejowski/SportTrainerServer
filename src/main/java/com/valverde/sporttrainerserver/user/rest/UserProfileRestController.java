package com.valverde.sporttrainerserver.user.rest;

import com.valverde.sporttrainerserver.user.dto.UserDataDTO;
import com.valverde.sporttrainerserver.user.service.UserProfileService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CommonsLog
@CrossOrigin
@RestController
public class UserProfileRestController {

    @GetMapping("/{username}/profile/getUserData")
    public ResponseEntity<UserDataDTO> getUserData(final @PathVariable String username) {
        try {
            final UserDataDTO userData = userProfileService.findUserData(username);
            return new ResponseEntity<>(userData, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception while getting userData for username "+username, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public UserProfileRestController(final UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    private final UserProfileService userProfileService;
}