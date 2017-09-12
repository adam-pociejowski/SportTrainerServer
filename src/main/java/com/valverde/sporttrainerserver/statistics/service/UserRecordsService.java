package com.valverde.sporttrainerserver.statistics.service;

import com.valverde.sporttrainerserver.activity.enums.ActivityType;
import com.valverde.sporttrainerserver.statistics.repository.ActivityRecordsRepository;
import com.valverde.sporttrainerserver.statistics.dto.ActivityStatsDTO;
import com.valverde.sporttrainerserver.statistics.dto.UserStatsSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class UserRecordsService {

    @Transactional
    public UserStatsSummaryDTO findUserRecords(final String username,
                                               final Date intervalBegin,
                                               final Date intervalEnd,
                                               final ActivityType activityType) {
        final List<ActivityStatsDTO> records = activityRecordsRepository
                .findRecordsForInterval(username, intervalBegin, intervalEnd, activityType);
        final UserStatsSummaryDTO userRecords = new UserStatsSummaryDTO();
        userRecords.setIntervalBegin(intervalBegin);
        userRecords.setIntervalEnd(intervalEnd);
        userRecords.setStats(records);
        return userRecords;
    }

    @Autowired
    public UserRecordsService(ActivityRecordsRepository activityRecordsRepository) {
        this.activityRecordsRepository = activityRecordsRepository;
    }

    private final ActivityRecordsRepository activityRecordsRepository;
}