package com.valverde.sporttrainerserver.statistics.util;

import com.valverde.sporttrainerserver.activity.dto.ActivityDTO;
import com.valverde.sporttrainerserver.activity.dto.TrackPointDTO;
import com.valverde.sporttrainerserver.statistics.dto.ActivityStatsDTO;
import com.valverde.sporttrainerserver.statistics.enums.RecordMeasureType;
import com.valverde.sporttrainerserver.statistics.enums.RecordType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ActivityRecordsUtil {

    public static void calculateActivityRecords(ActivityDTO activity) {
        List<RecordType> recordTypes = generateRunningRecordsTypes(activity);
        List<ActivityStatsDTO> records = calculateRecords(activity.getTrackPoints(), recordTypes, activity.getId());
        activity.setActivityRecords(records);
    }

    private static List<ActivityStatsDTO> calculateRecords(List<TrackPointDTO> trackPoints,
                                                           List<RecordType> recordTypes,
                                                           Long activityId) {
        List<ActualRecord> actualRecords = generateActualRecords(recordTypes, trackPoints.get(0));
        for (TrackPointDTO point : trackPoints) {
            for (ActualRecord record : actualRecords) {
                RecordMeasureType measureType = record.getType().getMeasureType();
                double actualDistance = point.getDistance() - record.getStartPoint().getDistance();
                long actualTime = point.getTime() - record.getStartPoint().getTime();
                if (measureType.equals(RecordMeasureType.DISTANCE) && actualDistance >= record.getType().getValue()) {
                    DistanceRecordUtil.calculateBestRecordTime(trackPoints, point, record);
                } else if (measureType.equals(RecordMeasureType.TIME) && actualTime <= record.getType().getValue()) {
                    TimeRecordUtil.calculateBestRecordTime(trackPoints, point, record);
                }
            }
        }
        return createActivityRecords(actualRecords, activityId);
    }

    private static List<ActivityStatsDTO> createActivityRecords(List<ActualRecord> actualRecords, Long activityId) {
        List<ActivityStatsDTO> activityRecords = new ArrayList<>();
        for (ActualRecord actualRecord : actualRecords) {
            ActivityStatsDTO activityRecord = new ActivityStatsDTO();
            activityRecord.setActivityId(activityId);
            activityRecord.setType(actualRecord.getType());
            if (actualRecord.getType().getMeasureType().equals(RecordMeasureType.DISTANCE)) {
                activityRecord.setValue(DistanceRecordUtil.getResult(actualRecord));
            } else if (actualRecord.getType().getMeasureType().equals(RecordMeasureType.TIME)) {
                activityRecord.setValue(TimeRecordUtil.getResult(actualRecord));
            }
            activityRecords.add(activityRecord);
        }
        return activityRecords;
    }

    private static List<ActualRecord> generateActualRecords(List<RecordType> recordTypes,
                                                     TrackPointDTO startPoint) {
        List<ActualRecord> actualRecords = new ArrayList<>();
        for (RecordType type: recordTypes) {
            ActualRecord record = new ActualRecord();
            record.setType(type);
            record.setStartPoint(startPoint);
            record.setEndPoint(startPoint);
            actualRecords.add(record);
        }
        return actualRecords;
    }

    private static List<RecordType> generateRunningRecordsTypes(ActivityDTO activity) {
        List<RecordType> filteredRecordsTypes = new ArrayList<>();
        addFilteredRecordTypes(Arrays.asList(
                RecordType.ONE_KILOMETER,
                RecordType.TWO_KILOMETERS,
                RecordType.THREE_KILOMETERS,
                RecordType.FIVE_KILOMETERS),
                activity.getDistance(),
                filteredRecordsTypes);
        addFilteredRecordTypes(
                Collections.singletonList(RecordType.COPPER_TEST),
                activity.getTotalTime(),
                filteredRecordsTypes);
        return filteredRecordsTypes;
    }

    private static void addFilteredRecordTypes(List<RecordType> recordsTypes,
                                        double stat,
                                        List<RecordType> filteredRecordsTypes) {
        for (RecordType type : recordsTypes) {
            if (type.getValue() < stat) {
                filteredRecordsTypes.add(type);
            }
        }
    }

    static class NoEndTrackPointException extends RuntimeException {}
}