package com.valverde.sporttrainerserver.web.api;

import com.valverde.sporttrainerserver.enums.ActivityType;
import com.valverde.sporttrainerserver.service.ActivityService;
import com.valverde.sporttrainerserver.service.UserRecordsService;
import com.valverde.sporttrainerserver.web.dto.ActivityDTO;
import com.valverde.sporttrainerserver.web.dto.UserRecordsDTO;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/{username}/upload/activity")
    public HttpStatus upload(@PathVariable String username,
                             @RequestParam MultipartFile file) {
        try {
            activityService.upload(file, username, ActivityType.RUNNING);
            log.info("Activity uploaded");
            return HttpStatus.OK;
        } catch (Exception e) {
            log.error("Problem while uploading activity", e);
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @GetMapping("/{username}/getRecords")
    public ResponseEntity<UserRecordsDTO> getRecords(@PathVariable String username) {
        try {
            UserRecordsDTO userRecords = userRecordsService.findUserRecords(username, null, null);
            return new ResponseEntity<>(userRecords, HttpStatus.OK);
        } catch (Exception e) {
            log.error(TAG + "Problem while getting info about records", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{username}/getActivities")
    public ResponseEntity<Page<ActivityDTO>> getPage(@PathVariable String username,
                                                               Pageable pageable) {
        try {
            Page<ActivityDTO> activities = activityService.findSortedActivities(pageable, username);
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

    @Autowired
    public ActivityRestController(ActivityService activityService, UserRecordsService userRecordsService) {
        this.activityService = activityService;
        this.userRecordsService = userRecordsService;
    }

    private final static String TAG = "["+ActivityService.class.getSimpleName()+"] ";

    private final ActivityService activityService;

    private final UserRecordsService userRecordsService;
}
