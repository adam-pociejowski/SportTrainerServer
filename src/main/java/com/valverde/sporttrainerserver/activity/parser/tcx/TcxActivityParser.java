package com.valverde.sporttrainerserver.activity.parser.tcx;

import com.valverde.sporttrainerserver.activity.enums.ActivityOrigin;
import com.valverde.sporttrainerserver.activity.enums.ActivityType;
import com.valverde.sporttrainerserver.activity.parser.ActivityParser;
import com.valverde.sporttrainerserver.activity.util.FileUtils;
import com.valverde.sporttrainerserver.activity.dto.ActivityDTO;
import com.valverde.sporttrainerserver.activity.dto.TrackPointDTO;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TcxActivityParser implements ActivityParser {

    @Override
    public ActivityDTO parse(final MultipartFile file, final ActivityType type) throws Exception {
        final Element rootElement = FileUtils.getXmlRoot(file);
        Namespace namespace = rootElement.getNamespace();
        Element activityElement = getActivityElement(rootElement, namespace);
        Element lapElement = activityElement.getChild(LAP, namespace);
        List<Element> trackPointElements = getTrackPointsElements(lapElement, namespace);
        List<TrackPointDTO> trackPoints = parseTrackPoints(trackPointElements, namespace);

        String dateString = activityElement.getChildText(ID, namespace);
        Long totalTime = convertSecondsToTime(lapElement.getChildText(TOTAL_TIME, namespace));
        Integer calories = Integer.parseInt(lapElement.getChildText(CALORIES, namespace));
        Double distance = toDouble(lapElement.getChildText(DISTANCE_METERS, namespace));
        ActivityDTO activity = new ActivityDTO();
        activity.setDate(convertToDate(dateString));
        activity.setTrackPoints(trackPoints);
        activity.setTotalTime(totalTime);
        activity.setCalories(calories);
        activity.setDistance(distance);
        activity.setType(type);
        activity.setOrigin(ActivityOrigin.TCX_UPLOAD);
        return activity;
    }

    private List<TrackPointDTO> parseTrackPoints(List<Element> trackPointElements,
                                                 Namespace namespace) {
        try {
            List<TrackPointDTO> trackPoints = new ArrayList<>();
            String timeString = trackPointElements.get(0).getChildText(TIME, namespace);
            Long startTime = convertToTime(timeString);
            for (Element element : trackPointElements) {
                timeString = element.getChildText(TIME, namespace);
                long pointTime = convertToTime(timeString) - startTime;
                String distanceMeters = element.getChildText(DISTANCE_METERS, namespace);
                String altitudeMeters = element.getChildText(ALTITUDE_METERS, namespace);
                Element positionElement = element.getChild(POSITION, namespace);
                String latitude = positionElement.getChildText(LATITUDE, namespace);
                String longitude = positionElement.getChildText(LONGITUDE, namespace);
                TrackPointDTO trackPoint = new TrackPointDTO();
                trackPoint.setDistance(toDouble(distanceMeters));
                trackPoint.setAltitude(toDouble(altitudeMeters));
                trackPoint.setLatitude(toDouble(latitude));
                trackPoint.setLongitude(toDouble(longitude));
                trackPoint.setTime(pointTime);
                trackPoints.add(trackPoint);
            }
            return trackPoints;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Double toDouble(String s) {
        return s == null ? null : Double.parseDouble(s);
    }

    private List<Element> getTrackPointsElements(Element lapElement, Namespace namespace) {
        Element trackElement = lapElement.getChild(TRACK, namespace);
        return trackElement.getChildren(TRACK_POINT, namespace);
    }

    private Element getActivityElement(Element root, Namespace namespace) {
        Element activities = root.getChild(ACTIVITIES, namespace);
        List<Element> activity = activities.getChildren(ACTIVITY, namespace);
        if (activity.size() == 0) {
            throw new NoActivitiesException();
        } else if (activity.size() > 1) {
            throw new MoreThanOneActivityException();
        }
        return activity.get(0);
    }

    private Date convertToDate(String timeString) throws Exception {
        return (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")).parse(timeString);
    }

    private Long convertToTime(String timeString) throws Exception {
        return (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")).parse(timeString).getTime();
    }

    private Long convertSecondsToTime(String seconds) {
        return (long)(toDouble(seconds) * 1000.0);
    }

    private class NoActivitiesException extends RuntimeException{}

    private class MoreThanOneActivityException extends RuntimeException{}

    private static  final String ACTIVITY = "Activity";
    private static  final String ACTIVITIES = "Activities";
    private static  final String TRACK_POINT = "Trackpoint";
    private static  final String TRACK = "Track";
    private static  final String TIME = "Time";
    private static  final String DISTANCE_METERS = "DistanceMeters";
    private static  final String ALTITUDE_METERS = "AltitudeMeters";
    private static  final String POSITION = "Position";
    private static  final String LATITUDE = "LatitudeDegrees";
    private static  final String LONGITUDE = "LongitudeDegrees";
    private static  final String ID = "Id";
    private static  final String LAP = "Lap";
    private static  final String TOTAL_TIME = "TotalTimeSeconds";
    private static  final String CALORIES = "Calories";
}