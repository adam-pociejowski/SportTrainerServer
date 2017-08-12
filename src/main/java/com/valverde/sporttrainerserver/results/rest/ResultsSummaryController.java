package com.valverde.sporttrainerserver.results.rest;

import com.valverde.sporttrainerserver.results.dto.ResultsSummaryDTO;
import com.valverde.sporttrainerserver.results.service.ResultsSummaryService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@CommonsLog
@RestController
public class ResultsSummaryController {

    @GetMapping("/{username}/getSummary")
    public ResponseEntity<ResultsSummaryDTO> getSummary(@PathVariable String username) {
        try {
            ResultsSummaryDTO resultsSummary = resultsSummaryService.findResultsSummary(username, null, null);
            return new ResponseEntity<>(resultsSummary, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Problem while getting info about summary for user: "+username, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResultsSummaryController(ResultsSummaryService resultsSummaryService) {
        this.resultsSummaryService = resultsSummaryService;
    }

    private final ResultsSummaryService resultsSummaryService;
}