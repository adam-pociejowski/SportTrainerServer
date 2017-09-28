package com.valverde.sporttrainerserver.zwift.util;

import com.valverde.sporttrainerserver.zwift.dto.RiderStateDTO;
import org.junit.Test;
import static org.junit.Assert.*;

public class ZwiftUtilsTest {

    @Test
    public void shouldReturnSameResultIfDistanceIsAccurate() {
        final RiderStateDTO riderState = createRiderState(RIDER_TIME, RIDER_DISTANCE);
        assertEquals(10, ZwiftUtils.calculateAccurateTime(100, riderState));
    }

    @Test
    public void shouldReturnFasterResultIfDistanceIsGreater() {
        final RiderStateDTO riderState = createRiderState(RIDER_TIME, RIDER_DISTANCE);
        assertEquals(8, ZwiftUtils.calculateAccurateTime(80, riderState));
    }

    @Test
    public void shouldReturnSlowerResultIfDistanceIsLesser() {
        final RiderStateDTO riderState = createRiderState(RIDER_TIME, RIDER_DISTANCE);
        assertEquals(12, ZwiftUtils.calculateAccurateTime(120, riderState));
    }

    @Test
    public void shouldReturnFasterResultAndRoundedToHigherValueIfDistanceIsGreater() {
        final RiderStateDTO riderState = createRiderState(RIDER_TIME, RIDER_DISTANCE);
        assertEquals(8, ZwiftUtils.calculateAccurateTime(76, riderState));
    }

    @Test
    public void shouldReturnFasterResultAndRoundedToLowerValueIfDistanceIsGreater() {
        final RiderStateDTO riderState = createRiderState(RIDER_TIME, RIDER_DISTANCE);
        assertEquals(7, ZwiftUtils.calculateAccurateTime(74, riderState));
    }

    private RiderStateDTO createRiderState(final int time, final int distance) {
        final RiderStateDTO riderState = new RiderStateDTO();
        riderState.setDistance(distance);
        riderState.setTime(time);
        return riderState;
    }

    private static final Integer RIDER_DISTANCE = 100;

    private static final Integer RIDER_TIME = 10;
}