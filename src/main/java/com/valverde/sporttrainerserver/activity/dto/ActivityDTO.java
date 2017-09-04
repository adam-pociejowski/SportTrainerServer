package com.valverde.sporttrainerserver.activity.dto;

import com.valverde.sporttrainerserver.activity.entity.Activity;
import com.valverde.sporttrainerserver.activity.enums.ActivityOrigin;
import com.valverde.sporttrainerserver.base.entity.User;
import com.valverde.sporttrainerserver.activity.enums.ActivityType;
import com.valverde.sporttrainerserver.statistics.dto.ActivityStatsDTO;
import lombok.Data;
import org.modelmapper.ModelMapper;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class ActivityDTO {

    private Long id;

    private List<TrackPointDTO> trackPoints;

    private List<ActivityStatsDTO> activityRecords;

    private List<SplitDTO> splits;

    private ActivityType type;

    private Date date;

    private Long totalTime;

    private Double distance;

    private Integer calories;

    private ActivityOrigin origin;

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
