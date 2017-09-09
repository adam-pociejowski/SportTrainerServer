package com.valverde.sporttrainerserver.base.util;

import com.valverde.sporttrainerserver.activity.dto.TrackPointDTO;

public class LocationUtils {
    public static double getDistanceInKm(TrackPointDTO firstEvent, TrackPointDTO secondEvent) {
        final double R = 6367.0;
        double lat1 = firstEvent.getLatitude();
        double lng1 = firstEvent.getLongitude();
        double lat2 = secondEvent.getLatitude();
        double lng2 = secondEvent.getLongitude();

        double factor = Math.PI / 180.0;
        double dlng = (lng2 - lng1) * factor;
        double dlat = (lat2 - lat1) * factor;
        double a = Math.pow(Math.sin(dlat / 2.0), 2.0) + Math.cos(lat1 * factor) *
                Math.cos(lat2 * factor) * Math.pow(Math.sin(dlng / 2.0), 2.0);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1.0 - a));
        return R * c;
    }
}