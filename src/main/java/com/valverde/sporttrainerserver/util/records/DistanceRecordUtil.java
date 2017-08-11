package com.valverde.sporttrainerserver.util.records;

import com.valverde.sporttrainerserver.enums.RecordType;
import com.valverde.sporttrainerserver.util.ActivityUtils;
import com.valverde.sporttrainerserver.web.dto.TrackPointDTO;
import java.util.List;

class DistanceRecordUtil {

    static void calculateBestRecordTime(List trackPoints, TrackPointDTO endPoint, ActualRecord record) {
        int index = trackPoints.indexOf(record.getStartPoint());
        while(index < trackPoints.size() &&
                isGreaterOrEqualsDistance(endPoint, (TrackPointDTO)trackPoints.get(index), record)) {
            TrackPointDTO actualStartPoint = (TrackPointDTO)trackPoints.get(index++);
            if (isBetterTime(actualStartPoint, endPoint, record)) {
                setRecordTrackPoints(record, actualStartPoint, endPoint);
            }
        }
    }

    static double getResult(ActualRecord record) {
        long time = record.getStartPoint().getTime() - record.getEndPoint().getTime();
        double distance = record.getStartPoint().getDistance() - record.getEndPoint().getDistance();
        return ActivityUtils.getNormalizedSplitTime(record.getType().getValue(), distance, time);
    }

    private static boolean isGreaterOrEqualsDistance(TrackPointDTO endPoint,
                                                     TrackPointDTO actualStartPoint,
                                                     ActualRecord record) {
        return endPoint.getDistance() - actualStartPoint.getDistance() >= record.getType().getValue();
    }

    private static boolean isBetterTime(TrackPointDTO actualStartPoint, TrackPointDTO endPoint, ActualRecord record) {
        return isNotInitializedRecord(record) ||
                (getRecordTime(actualStartPoint, endPoint, record.getType()) <
                        getRecordTime(record.getStartPoint(), record.getEndPoint(), record.getType()));
    }

    private static boolean isNotInitializedRecord(ActualRecord record) {
        return record.getStartPoint() == record.getEndPoint();
    }

    private static Long getRecordTime(TrackPointDTO startPoint, TrackPointDTO endPoint, RecordType type) {
        if (endPoint != null) {
            Long time = endPoint.getTime() - startPoint.getTime();
            Double distance = endPoint.getDistance() - startPoint.getDistance();
            return ActivityUtils.getNormalizedSplitTime(type.getValue(), distance, time);
        }
        throw new ActivityRecordsUtil.NoEndTrackPointException();
    }

    private static void setRecordTrackPoints(ActualRecord record, TrackPointDTO startPoint, TrackPointDTO endPoint) {
        record.setStartPoint(startPoint);
        record.setEndPoint(endPoint);
    }
}