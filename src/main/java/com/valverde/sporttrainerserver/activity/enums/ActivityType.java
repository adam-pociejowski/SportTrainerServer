package com.valverde.sporttrainerserver.activity.enums;

import lombok.Getter;

public enum ActivityType {
    RUNNING("Running", 1);

    @Getter
    private String typeName;

    @Getter
    private Integer splitInterval;

    ActivityType(String typeName, Integer splitInterval) {
        this.typeName = typeName;
        this.splitInterval = splitInterval;
    }
}
