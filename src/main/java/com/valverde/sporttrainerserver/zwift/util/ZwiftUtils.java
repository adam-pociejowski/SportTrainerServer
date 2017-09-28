package com.valverde.sporttrainerserver.zwift.util;

import com.valverde.sporttrainerserver.zwift.dto.RiderStateDTO;

public class ZwiftUtils {

    public static int calculateAccurateTime(final Integer accurateDistance,
                                            final RiderStateDTO riderState) {
        final Integer riderStateTime = riderState.getTime();
        final Integer riderStateDistance = riderState.getDistance();
        final Double distanceFactor = accurateDistance / (double)riderStateDistance;
        return (int) Math.round(riderStateTime * distanceFactor);
    }
}