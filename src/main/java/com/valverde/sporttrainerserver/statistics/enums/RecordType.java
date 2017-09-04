package com.valverde.sporttrainerserver.statistics.enums;

import lombok.Getter;

public enum RecordType {
    ONE_KILOMETER(RecordMeasureType.DISTANCE, 1000.0),
    TWO_KILOMETERS(RecordMeasureType.DISTANCE, 2000.0),
    THREE_KILOMETERS(RecordMeasureType.DISTANCE, 3000.0),
    FIVE_KILOMETERS(RecordMeasureType.DISTANCE, 5000.0),
    COPPER_TEST(RecordMeasureType.TIME, 720000.0),
    LONGEST_DISTANCE(RecordMeasureType.DISTANCE, 0.0),
    LONGEST_TIME(RecordMeasureType.TIME, 0.0),
    BEST_AVG_SPEED(RecordMeasureType.SPEED, 0.0),
    BEST_AVG_PACE(RecordMeasureType.PACE, 0.0);

    @Getter
    private RecordMeasureType measureType;

    @Getter
    private Double value;

    RecordType(RecordMeasureType measureType, Double value) {
        this.measureType = measureType;
        this.value = value;
    }
}
