package com.valverde.sporttrainerserver.statistics.rest;

import com.valverde.sporttrainerserver.statistics.dto.ResultsSummaryDTO;
import com.valverde.sporttrainerserver.statistics.enums.ResultSummaryType;
import com.valverde.sporttrainerserver.statistics.service.ChartsService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CommonsLog
@CrossOrigin
@RestController
@RequestMapping("charts")
public class ChartsController {

    @GetMapping("/{username}/getStatsForInterval")
    public ResponseEntity<List<ResultsSummaryDTO>> getRecords(@PathVariable String username,
                                                              @RequestParam Integer interval,
                                                              @RequestParam Integer amount,
                                                              @RequestParam ResultSummaryType type) {
        try {
            final List<ResultsSummaryDTO> stats = chartsService.generateStatsForInterval(username, interval, amount, type);
            return new ResponseEntity<>(stats, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ChartsController(ChartsService chartsService) {
        this.chartsService = chartsService;
    }

    private final ChartsService chartsService;
}