package com.valverde.sporttrainerserver.activity.parser.gpx;

import com.topografix.gpx._1._1.GpxType;
import com.topografix.gpx._1._1.TrkType;
import com.topografix.gpx._1._1.TrksegType;
import com.topografix.gpx._1._1.WptType;
import com.valverde.sporttrainerserver.activity.dto.ActivityDTO;
import com.valverde.sporttrainerserver.activity.dto.TrackPointDTO;
import com.valverde.sporttrainerserver.activity.enums.ActivityOrigin;
import com.valverde.sporttrainerserver.activity.enums.ActivityType;
import com.valverde.sporttrainerserver.activity.parser.ActivityParser;
import com.valverde.sporttrainerserver.activity.service.JaxbService;
import com.valverde.sporttrainerserver.activity.util.FileUtils;
import com.valverde.sporttrainerserver.base.util.AppUtils;
import com.valverde.sporttrainerserver.base.util.LocationUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GpxActivityParser implements ActivityParser {

    @Override
    public synchronized ActivityDTO parse(final MultipartFile multipartFile, final ActivityType type) throws Exception {
        final File file = FileUtils.convertToFile(multipartFile);
        try {
            final GpxType gpxType = jaxbService.unMarshall(file, GpxType.class);
            final ActivityDTO activity = parseActivity(gpxType);
            activity.setOrigin(ActivityOrigin.GPX_UPLOAD);
            activity.setType(type);
            return activity;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            FileUtils.deleteFile(file);
        }
    }

    @NotNull
    private ActivityDTO parseActivity(final GpxType gpxType) {
        final ActivityDTO activity = new ActivityDTO();
        activity.setDate(AppUtils.getDate(gpxType.getMetadata().getTime()));
        activity.setCalories(0);
        final List<TrackPointDTO> trackPoints = parseTrackPoints(gpxType.getTrk());
        activity.setTrackPoints(trackPoints);
        final TrackPointDTO lastTrackPoint = trackPoints.get(trackPoints.size() - 1);
        activity.setTotalTime(lastTrackPoint.getTime());
        activity.setDistance(lastTrackPoint.getDistance());
        return activity;
    }

    @NotNull
    private List<TrackPointDTO> parseTrackPoints(final List<TrkType> trkList) {
        checkGpxTypeListCorrectness(trkList);
        final TrkType trkType = trkList.get(0);
        final List<TrksegType> segmentTypeList = trkType.getTrkseg();
        checkGpxTypeListCorrectness(segmentTypeList);

        final TrksegType segmentType = segmentTypeList.get(0);
        final List<WptType> points = segmentType.getTrkpt();
        return convertToTrackPoints(points);
    }

    @NotNull
    private List<TrackPointDTO> convertToTrackPoints(List<WptType> points) {
        final List<TrackPointDTO> trackPoints = new ArrayList<>();
        TrackPointDTO previousPoint = null;
        Date firstPointDate = null;
        Integer index = 0;
        double distanceInMeters = 0.0;
        for (WptType point : points) {
            final TrackPointDTO trackPoint = createAndSetBasicTrackPoint(point);
            if (isFirstTrackPoint(previousPoint, firstPointDate)) {
                trackPoint.setDistance(0.0);
                trackPoint.setTime(0L);
                firstPointDate = AppUtils.getDate(point.getTime());
            } else {
                double distanceBetweenPointsInKm = LocationUtils.getDistanceInKm(previousPoint, trackPoint);
                distanceInMeters += distanceBetweenPointsInKm * 1000.0;
            }

            if (isPointForInterval(index) || isLastPoint(points, index)) {
                trackPoint.setTime(getActualPointTime(firstPointDate, AppUtils.getDate(point.getTime())));
                trackPoint.setDistance(distanceInMeters);
                trackPoints.add(trackPoint);
            }
            previousPoint = trackPoint;
            index++;
        }
        return trackPoints;
    }

    @NotNull
    private TrackPointDTO createAndSetBasicTrackPoint(WptType point) {
        final TrackPointDTO trackPoint = new TrackPointDTO();
        trackPoint.setLatitude(getDouble(point.getLat()));
        trackPoint.setLongitude(getDouble(point.getLon()));
        trackPoint.setAltitude(getDouble(point.getEle()));
        return trackPoint;
    }

    private boolean isPointForInterval(Integer index) {
        return index % TRACK_POINT_INTERVAL == 0;
    }

    private boolean isLastPoint(List<WptType> points, Integer index) {
        return index == points.size() - 1;
    }

    @NotNull
    private Long getActualPointTime(Date firstPointDate, Date actualPointDate) {
        return actualPointDate.getTime() - firstPointDate.getTime();
    }

    @NotNull
    private Double getActualPointDistance(TrackPointDTO previousPoint, Double actual) {
        return AppUtils.isNull(previousPoint) ? actual : previousPoint.getDistance() + actual;
    }

    private boolean isFirstTrackPoint(final TrackPointDTO previousPoint, final Date firstPointDate) {
        return AppUtils.isNull(previousPoint) && AppUtils.isNull(firstPointDate);
    }

    private Double getDouble(final BigDecimal value) {
        return AppUtils.isNotNull(value) ? value.doubleValue() : null;
    }

    private void checkGpxTypeListCorrectness(List list) {
        if (AppUtils.isNull(list)) {
            throw new RuntimeException("List cannot be null");
        } else if (list.size() != 1) {
            throw new NotOneGpxTypeElementException("Wrong number of elements actual: "+list.size()+", expected: 1");
        }
    }

    private class NotOneGpxTypeElementException extends RuntimeException {
        NotOneGpxTypeElementException(String message) {
            super(message);
        }
    }

    public GpxActivityParser(JaxbService jaxbService) {
        this.jaxbService = jaxbService;
    }

    private final JaxbService jaxbService;

    private final static Integer TRACK_POINT_INTERVAL = 5;
}
