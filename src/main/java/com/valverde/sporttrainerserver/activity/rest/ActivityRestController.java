package com.valverde.sporttrainerserver.activity.rest;

import com.valverde.sporttrainerserver.activity.dto.ActivityDTO;
import com.valverde.sporttrainerserver.activity.enums.ActivityOrigin;
import com.valverde.sporttrainerserver.activity.enums.ActivityType;
import com.valverde.sporttrainerserver.activity.service.ActivityService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CommonsLog
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ActivityRestController {

    @PostMapping("/{username}/{activityType}/upload/activity")
    public HttpStatus upload(@PathVariable String username,
                             @PathVariable ActivityType activityType,
                             @RequestParam MultipartFile file) {
        try {
            activityService.upload(file, username, activityType);
            log.info("User "+username+" uploaded new activity");
            return HttpStatus.OK;
        } catch (Exception e) {
            log.error("Problem while uploading activity. User: "+username, e);
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @PostMapping("/{username}/uploadApp/activity")
    public HttpStatus uploadFromApp(@PathVariable String username,
                                    @RequestBody ActivityDTO activity) {
        try {
            activity.setOrigin(ActivityOrigin.SPORT_TRAINER_APP_ANDROID);
            activityService.calculateAndSave(username, activity.getType(), activity);
            log.info("User "+username+" uploaded new activity from app");
            return HttpStatus.OK;
        } catch (Exception e) {
            log.error("Problem while uploading activity from app. User: "+username, e);
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @GetMapping("/{username}/{activityType}/getActivities")
    public ResponseEntity<Page<ActivityDTO>> getPage(@PathVariable String username,
                                                     @PathVariable ActivityType activityType,
                                                     Pageable pageable) {
        try {
            final Page<ActivityDTO> activities = activityService.findSortedActivities(pageable, username, activityType);
            return new ResponseEntity<>(activities, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Problem while getting info about activities for user: "+username, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{username}/delete/{activityId}")
    public HttpStatus delete(@PathVariable String username,
                             @PathVariable Long activityId) {
        try {
            activityService.delete(username, activityId);
            return HttpStatus.OK;
        } catch (Exception e) {
            log.error("Problem while deleting activity id: "+activityId+", for user: "+username, e);
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @GetMapping("/{username}/activities/{activityId}")
    public ResponseEntity<ActivityDTO> getSingle(@PathVariable String username,
                                @PathVariable Long activityId) {
        try {
            return new ResponseEntity<>(activityService.findActivityById(activityId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ActivityRestController(ActivityService activityService) {
        this.activityService = activityService;
    }

    private final ActivityService activityService;
}