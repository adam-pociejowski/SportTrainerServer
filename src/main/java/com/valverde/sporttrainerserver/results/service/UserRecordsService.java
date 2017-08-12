package com.valverde.sporttrainerserver.results.service;

import com.valverde.sporttrainerserver.results.repository.ActivityRecordsRepository;
import com.valverde.sporttrainerserver.results.dto.ActivityRecordDTO;
import com.valverde.sporttrainerserver.results.dto.UserRecordsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class UserRecordsService {

    @Transactional
    public UserRecordsDTO findUserRecords(String username, Date intervalBegin, Date intervalEnd) {
        List<ActivityRecordDTO> records = activityRecordsRepository.findRecordsForInterval(username, intervalBegin, intervalEnd);
        UserRecordsDTO userRecords = new UserRecordsDTO();
        userRecords.setIntervalBegin(intervalBegin);
        userRecords.setIntervalEnd(intervalEnd);
        userRecords.setRecords(records);
        return userRecords;
    }

    @Autowired
    public UserRecordsService(ActivityRecordsRepository activityRecordsRepository) {
        this.activityRecordsRepository = activityRecordsRepository;
    }

    private final ActivityRecordsRepository activityRecordsRepository;
}