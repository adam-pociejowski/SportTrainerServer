package com.valverde.sporttrainerserver.statistics.rest;

import com.valverde.sporttrainerserver.statistics.dto.UserStatsSummaryDTO;
import com.valverde.sporttrainerserver.statistics.service.UserRecordsService;
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
public class RecordsRestController {

    @GetMapping("/{username}/getRecords")
    public ResponseEntity<UserStatsSummaryDTO> getRecords(@PathVariable String username) {
        try {
            UserStatsSummaryDTO userRecords = userRecordsService.findUserRecords(username, null, null);
            return new ResponseEntity<>(userRecords, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Problem while getting info about statistics for user: "+username, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public RecordsRestController(UserRecordsService userRecordsService) {
        this.userRecordsService = userRecordsService;
    }

    private final UserRecordsService userRecordsService;
}