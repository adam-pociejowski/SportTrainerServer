package com.valverde.sporttrainerserver.util;

import com.valverde.sporttrainerserver.entity.Activity;
import com.valverde.sporttrainerserver.web.dto.ActivityDTO;
import com.valverde.sporttrainerserver.web.dto.TrackPointDTO;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

public class ActivityUtils {

    public static double getAvgSpeed(Activity activity) {
        return activity.getDistance() / (double) (activity.getTotalTime() / 3600);
    }

    public static double getAvgPace(double distance, long time) {
        return (double) (time / 60) / distance;
    }

    public static long getNormalizedSplitTime(double splitInterval, double splitAccurateDistance, long splitTime) {
        double factor = splitAccurateDistance / splitInterval;
        return (long) ((double) splitTime / factor);
    }

    public static double getNormalizedSplitDistance(double splitIntervalTime, double splitAccurateDistance, long splitTime) {
        double factor = (double) splitTime / splitIntervalTime;
        return splitAccurateDistance * factor;
    }

    public static void optimizeTrackPoints(List<ActivityDTO> activities) {
        activities.forEach(ActivityUtils::optimize);
    }

    private static void optimize(ActivityDTO activity) {
        List<TrackPointDTO> trackPoints = activity.getTrackPoints();
        OptimizedTrackPoints optimizedTrackPoints = new OptimizedTrackPoints();
        optimizedTrackPoints.setLastPoint(trackPoints.get(0));
        trackPoints.add(trackPoints.get(0));
        for (int i = 1; i < trackPoints.size(); i++) {
            TrackPointDTO actualPoint = trackPoints.get(i);
            if (optimizedTrackPoints.getPointsBetween().isEmpty() || canSkipTrackPoint(optimizedTrackPoints, actualPoint)) {
                optimizedTrackPoints.getPointsBetween().add(actualPoint);
            } else {
                optimizedTrackPoints.setLastPoint(actualPoint);
                optimizedTrackPoints.getTrackPoints().add(actualPoint);
                optimizedTrackPoints.getPointsBetween().clear();
            }
        }
        activity.setTrackPoints(optimizedTrackPoints.getTrackPoints());
    }

    private static boolean canSkipTrackPoint(OptimizedTrackPoints optimizedTrackPoints,
                                             TrackPointDTO actualPoint) {
        return optimizedTrackPoints.getPointsBetween().size() <= OPTIMIZE_TRACK_POINTS_MAX_SKIP_AMOUNT &&
                isPointsDeviationAdmissible(optimizedTrackPoints, actualPoint, OPTIMIZE_TRACK_POINTS_DEGREE);
    }

    private static boolean isPointsDeviationAdmissible(OptimizedTrackPoints optimizedTrackPoints,
                                                       TrackPointDTO actualPoint,
                                                       Double degree) {
        TrackPointDTO startPoint = optimizedTrackPoints.getLastPoint();
        Double c = calculateDistance(actualPoint, startPoint);
        Double cosAdmissible = Math.cos(Math.toRadians(degree));
        for (TrackPointDTO point : optimizedTrackPoints.getPointsBetween()) {
            Double a = calculateDistance(point, actualPoint);
            Double b = calculateDistance(point, startPoint);
            Double cosAlpha = (Math.pow(b, 2.0) + Math.pow(c, 2.0) - Math.pow(a, 2.0)) / (2 * b * c);
            if (cosAlpha < cosAdmissible)
                return false;
        }
        return true;
    }

    private static Double calculateDistance(TrackPointDTO point1, TrackPointDTO point2) {
        return Math.sqrt(Math.pow(point1.getLatitude() - point2.getLatitude(), 2.0) +
                (Math.pow(point1.getLongitude() - point2.getLongitude(), 2.0)));
    }

    private static final double OPTIMIZE_TRACK_POINTS_DEGREE = 5.0;

    private static final int OPTIMIZE_TRACK_POINTS_MAX_SKIP_AMOUNT = 6;

    @Data
    private static class OptimizedTrackPoints {

        private List<TrackPointDTO> trackPoints;

        private List<TrackPointDTO> pointsBetween;

        private TrackPointDTO lastPoint;

        OptimizedTrackPoints() {
            this.trackPoints = new ArrayList<>();
            this.pointsBetween = new ArrayList<>();
        }
    }
}
