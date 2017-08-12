package com.valverde.sporttrainerserver.results.dto;

import com.valverde.sporttrainerserver.results.entity.ActivityRecord;
import com.valverde.sporttrainerserver.results.enums.RecordType;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ActivityRecordDTO {

    private Long activityId;

    private RecordType type;

    private Double value;

    public static ActivityRecordDTO toDTO(ActivityRecord activityRecord) {
        return new ModelMapper().map(activityRecord, ActivityRecordDTO.class);
    }
}
