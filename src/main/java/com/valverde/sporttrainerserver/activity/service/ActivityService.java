package com.valverde.sporttrainerserver.activity.service;

import com.valverde.sporttrainerserver.activity.enums.ActivityOrigin;
import com.valverde.sporttrainerserver.base.service.UserService;
import com.valverde.sporttrainerserver.activity.entity.Activity;
import com.valverde.sporttrainerserver.base.entity.User;
import com.valverde.sporttrainerserver.activity.enums.ActivityType;
import com.valverde.sporttrainerserver.activity.repository.ActivityRepository;
import com.valverde.sporttrainerserver.activity.service.parser.ActivityParser;
import com.valverde.sporttrainerserver.activity.util.ActivitySplitUtil;
import com.valverde.sporttrainerserver.statistics.util.ActivityRecordsUtil;
import com.valverde.sporttrainerserver.activity.dto.ActivityDTO;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;

@Service
@CommonsLog
@Transactional
public class ActivityService {

    public void delete(String username, Long activityId) {
        Activity activity = activityRepository.findOne(activityId);
        if (activity == null)
            throw new ActivityNotFoundException("Activity id: "+activityId+" not found.");

        if (!activity.getUser().getUsername().equals(username))
            throw new ActivityIsOwnedByOtherUserException("Activity belongs to "+
                    activity.getUser().getUsername()+", not to "+username);

        activityRepository.delete(activityId);
        log.info(TAG + "Activity id: "+activityId+" deleted.");
    }

    public void upload(MultipartFile multipartFile, String username, ActivityType type) throws Exception {
        ActivityDTO activityDTO = tcxActivityParser.parse(multipartFile, type);
        calculateAndSave(username,
                type,
                activityDTO,
                ActivityOrigin.TCX_UPLOAD);
    }

    public void calculateAndSave(String username,
                                 ActivityType type,
                                 ActivityDTO activityDTO,
                                 ActivityOrigin origin) {
        ActivityRecordsUtil.calculateActivityRecords(activityDTO);
        ActivitySplitUtil.calculateSplits(type.getSplitInterval(), activityDTO);
        activityDTO.setOrigin(origin);
        save(activityDTO, userService.findByUsername(username));
    }

    public Page<ActivityDTO> findSortedActivities(Pageable pageable, String username) {
        Page<Activity> page = activityRepository.findAllByUsername(username, pageable);
        List<ActivityDTO> activityDTOs = new ArrayList<>();
        page.getContent().forEach(activity -> activityDTOs.add(ActivityDTO.toDTO(activity)));
        return new PageImpl<>(activityDTOs, pageable, page.getTotalElements());
    }

    public ActivityDTO findActivityById(Long activityId) {
        Activity activity = activityRepository.findOne(activityId);
        return ActivityDTO.toDTO(activity);
    }

    private void save(ActivityDTO activityDTO, User user) {
        Activity activity = ActivityDTO.toEntity(activityDTO, user);
        activityRepository.save(activity);
    }

    @Autowired
    public ActivityService(ActivityRepository activityRepository,
                           ActivityParser tcxActivityParser,
                           UserService userService) {
        this.activityRepository = activityRepository;
        this.userService = userService;
        this.tcxActivityParser = tcxActivityParser;
    }

    private final ActivityRepository activityRepository;

    private final ActivityParser tcxActivityParser;

    private final UserService userService;

    private final static String TAG = "["+ActivityService.class.getSimpleName()+"] ";

    private class ActivityNotFoundException extends RuntimeException {
        ActivityNotFoundException(String message) {
            super(message);
        }
    }

    private class ActivityIsOwnedByOtherUserException extends RuntimeException {
        ActivityIsOwnedByOtherUserException(String message) {
            super(message);
        }
    }
}
