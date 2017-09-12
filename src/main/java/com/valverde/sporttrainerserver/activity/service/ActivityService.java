package com.valverde.sporttrainerserver.activity.service;

import com.valverde.sporttrainerserver.activity.dto.ActivityDTO;
import com.valverde.sporttrainerserver.activity.entity.Activity;
import com.valverde.sporttrainerserver.activity.enums.ActivityType;
import com.valverde.sporttrainerserver.activity.repository.ActivityRepository;
import com.valverde.sporttrainerserver.activity.parser.ActivityParser;
import com.valverde.sporttrainerserver.activity.util.ActivitySplitUtil;
import com.valverde.sporttrainerserver.base.entity.User;
import com.valverde.sporttrainerserver.base.service.UserService;
import com.valverde.sporttrainerserver.statistics.util.ActivityRecordsUtil;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;
import static com.valverde.sporttrainerserver.activity.parser.enums.ActivityParserType.*;

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

    public void upload(final MultipartFile multipartFile,
                       final String username,
                       final ActivityType type) throws Exception {
        final ActivityParser activityParser = getActivityParser(multipartFile.getOriginalFilename());
        final ActivityDTO activity = activityParser.parse(multipartFile, type);
        calculateAndSave(username, type, activity);
    }

    public void calculateAndSave(String username,
                                 ActivityType type,
                                 ActivityDTO activityDTO) {
        ActivityRecordsUtil.calculateActivityRecords(activityDTO);
        ActivitySplitUtil.calculateSplits(type.getSplitInterval(), activityDTO);
        save(activityDTO, userService.findByUsername(username));
    }

    public Page<ActivityDTO> findSortedActivities(final Pageable pageable,
                                                  final String username,
                                                  final ActivityType activityType) {
        final Page<Activity> page;
        if (activityType.equals(ActivityType.ALL)) {
            page = activityRepository.findAllByUsername(username, pageable);
        } else {
            page = activityRepository.findAllByUsernameAndType(username, activityType, pageable);
        }
        final List<ActivityDTO> activityDTOs = new ArrayList<>();
        page.getContent().forEach(activity -> activityDTOs.add(ActivityDTO.toDTO(activity)));
        return new PageImpl<>(activityDTOs, pageable, page.getTotalElements());
    }

    public ActivityDTO findActivityById(Long activityId) {
        Activity activity = activityRepository.findOne(activityId);
        return ActivityDTO.toDTO(activity);
    }

    private ActivityParser getActivityParser(final String fileName) {
        final String extension = FilenameUtils.getExtension(fileName);
        if (extension.equalsIgnoreCase(GPX_PARSER.getFileExtension())) {
            return gpxActivityParser;
        } else if (extension.equalsIgnoreCase(TCX_PARSER.getFileExtension())) {
            return tcxActivityParser;
        }
        throw new RuntimeException("No parser found for file extension: "+extension);
    }

    private void save(ActivityDTO activityDTO, User user) {
        Activity activity = ActivityDTO.toEntity(activityDTO, user);
        activityRepository.save(activity);
    }

    @Autowired
    public ActivityService(ActivityRepository activityRepository,
                           ActivityParser tcxActivityParser,
                           ActivityParser gpxActivityParser, UserService userService) {
        this.activityRepository = activityRepository;
        this.gpxActivityParser = gpxActivityParser;
        this.userService = userService;
        this.tcxActivityParser = tcxActivityParser;
    }

    private final ActivityRepository activityRepository;

    private final ActivityParser tcxActivityParser;

    private final ActivityParser gpxActivityParser;

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
