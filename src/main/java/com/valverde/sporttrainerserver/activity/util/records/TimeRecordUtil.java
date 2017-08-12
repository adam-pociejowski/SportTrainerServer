package com.valverde.sporttrainerserver.activity.util.records;

import com.valverde.sporttrainerserver.activity.enums.RecordType;
import com.valverde.sporttrainerserver.activity.util.ActivityUtils;
import com.valverde.sporttrainerserver.activity.dto.TrackPointDTO;

import java.util.List;

class TimeRecordUtil {

    static void calculateBestRecordTime(List trackPoints, TrackPointDTO endPoint, ActualRecord record) {
        int index = trackPoints.indexOf(record.getStartPoint());
        while(index < trackPoints.size() &&
                isInRecordTimeMeasure(endPoint, (TrackPointDTO)trackPoints.get(index), record)) {
            TrackPointDTO actualStartPoint = (TrackPointDTO)trackPoints.get(index++);
            if (isBetterDistance(actualStartPoint, endPoint, record)) {
                setRecordTrackPoints(record, actualStartPoint, endPoint);
            }
        }
    }

    static double getResult(ActualRecord record) {
        return getRecordDistance(record.getStartPoint(), record.getEndPoint(), record.getType());
    }

    private static boolean isBetterDistance(TrackPointDTO endPoint, TrackPointDTO actualStartPoint, ActualRecord record) {
        return isNotInitializedRecord(record) ||
                (getRecordDistance(actualStartPoint, endPoint, record.getType()) >
                        getRecordDistance(record.getStartPoint(), record.getEndPoint(), record.getType()));
    }

    private static double getRecordDistance(TrackPointDTO startPoint, TrackPointDTO endPoint, RecordType type) {
        if (endPoint != null) {
            long time = startPoint.getTime() - endPoint.getTime();
            double distance = startPoint.getDistance() - endPoint.getDistance();
            return ActivityUtils.getNormalizedSplitDistance(type.getValue(), distance, time);
        } else {
            throw new ActivityRecordsUtil.NoEndTrackPointException();
        }
    }

    private static boolean isInRecordTimeMeasure(TrackPointDTO actualStartPoint,
                                                 TrackPointDTO endPoint,
                                                 ActualRecord record) {
        return getRecordTime(actualStartPoint, endPoint) <= record.getType().getValue();
    }

    private static boolean isNotInitializedRecord(ActualRecord record) {
        return record.getStartPoint() == record.getEndPoint();
    }

    private static Long getRecordTime(TrackPointDTO startPoint, TrackPointDTO endPoint) {
        if (endPoint != null)
            return endPoint.getTime() - startPoint.getTime();
        throw new ActivityRecordsUtil.NoEndTrackPointException();
    }

    private static void setRecordTrackPoints(ActualRecord record, TrackPointDTO startPoint, TrackPointDTO endPoint) {
        record.setStartPoint(startPoint);
        record.setEndPoint(endPoint);
    }
}