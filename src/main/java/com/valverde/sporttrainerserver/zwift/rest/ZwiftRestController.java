package com.valverde.sporttrainerserver.zwift.rest;

import com.valverde.sporttrainerserver.zwift.dto.ZwiftRankingDTO;
import com.valverde.sporttrainerserver.zwift.dto.ZwiftStartActivityDTO;
import com.valverde.sporttrainerserver.zwift.enums.ZwiftTrack;
import com.valverde.sporttrainerserver.zwift.service.ZwiftActivityService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CommonsLog
@RestController
public class ZwiftRestController {

    @PostMapping("/zwift/startActivity")
    public ResponseEntity<Long> startActivity(@RequestBody ZwiftStartActivityDTO zwiftStartActivity) {
        try {
            final Long activityId = zwiftActivityService.startActivity(zwiftStartActivity);
            return new ResponseEntity<>(activityId, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception while trying to start activity", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/zwift/getTrackRankingForActivity")
    public ResponseEntity<ZwiftRankingDTO> getTrackRankingForActivity(@RequestParam Long activityId,
                                                                      @RequestParam ZwiftTrack track) {
        try {
            final ZwiftRankingDTO rankingDTO = zwiftActivityService.refreshTrackRankingForActivity(activityId, track);
            return new ResponseEntity<>(rankingDTO, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception while trying to get ranking for activity", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/zwift/getTrackRanking")
    public ResponseEntity<ZwiftRankingDTO> getTrackRanking(@RequestParam Integer distance) {
        try {
            final ZwiftRankingDTO rankingDTO = zwiftActivityService.getTrackRanking(distance);
            return new ResponseEntity<>(rankingDTO, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception while trying to get track ranking", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/zwift/finishActivity")
    public ResponseEntity<HttpStatus> startActivity(@RequestParam Long activityId) {
        try {
            zwiftActivityService.finishActivity(activityId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception while trying to finish activity", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ZwiftRestController(final ZwiftActivityService zwiftActivityService) {
        this.zwiftActivityService = zwiftActivityService;
    }

    private final ZwiftActivityService zwiftActivityService;
}