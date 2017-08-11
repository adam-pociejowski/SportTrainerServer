package com.valverde.sporttrainerserver.web.dto;

import com.valverde.sporttrainerserver.entity.Activity;
import com.valverde.sporttrainerserver.entity.User;
import com.valverde.sporttrainerserver.enums.ActivityType;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class ActivityDTO {

    private Long id;

    private List<TrackPointDTO> trackPoints;

    private List<ActivityRecordDTO> activityRecords;

    private List<SplitDTO> splits;

    private ActivityType type;

    private Date date;

    private Long totalTime;

    private Double distance;

    private Integer calories;

    public static ActivityDTO toDTO(@NotNull Activity activity) {
        return new ModelMapper().map(activity, ActivityDTO.class);
    }

    public static Activity toEntity(@NotNull ActivityDTO activityDTO, @NotNull User user) {
        Activity activity = new ModelMapper().map(activityDTO, Activity.class);
        activity.getTrackPoints().forEach(trackPoint -> trackPoint.setActivity(activity));
        activity.getSplits().forEach(split -> split.setActivity(activity));
        activity.getActivityRecords().forEach(activityRecord -> activityRecord.setActivity(activity));
        activity.setUser(user);
        return activity;
    }
}
