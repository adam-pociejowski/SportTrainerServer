package com.valverde.sporttrainerserver.statistics.dto;

import com.valverde.sporttrainerserver.statistics.entity.ActivityRecord;
import com.valverde.sporttrainerserver.statistics.enums.RecordType;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ActivityStatsDTO {

    private Long activityId;

    private RecordType type;

    private Double value;

    public static ActivityStatsDTO toDTO(ActivityRecord activityRecord) {
        return new ModelMapper().map(activityRecord, ActivityStatsDTO.class);
    }
}
