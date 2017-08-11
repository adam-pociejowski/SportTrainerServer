package com.valverde.sporttrainerserver.util;

import com.valverde.sporttrainerserver.web.dto.ActivityDTO;
import com.valverde.sporttrainerserver.web.dto.SplitDTO;
import com.valverde.sporttrainerserver.web.dto.TrackPointDTO;
import java.util.ArrayList;
import java.util.List;

public class ActivitySplitUtil {

    public static void calculateSplits(Integer splitInterval, ActivityDTO activity) {
        List<SplitDTO> splits = new ArrayList<>();
        int actualSplitKilometer = splitInterval;
        long splitStartTime = 0L;
        double splitStartDistance = 0.0D;
        for (TrackPointDTO trackPoint : activity.getTrackPoints()) {
            if (trackPoint.getDistance() > actualSplitKilometer * 1000.0) {
                long splitTime = trackPoint.getTime() - splitStartTime;
                double splitDistance = trackPoint.getDistance() - splitStartDistance;
                SplitDTO split = new SplitDTO();
                split.setKilometerNumber(actualSplitKilometer);
                split.setTime(ActivityUtils.getNormalizedSplitTime(
                        splitInterval * 1000.0,
                        splitDistance,
                        splitTime));
                splits.add(split);
                splitStartTime = trackPoint.getTime();
                actualSplitKilometer += splitInterval;
                splitStartDistance = trackPoint.getDistance();
            }
        }
        activity.setSplits(splits);
    }
}