package com.valverde.sporttrainerserver.zwift.enums;

import lombok.Getter;

@Getter
public enum ZwiftTrack {
    WATOPIA_FLAT("Watopia flat", 10.3, 54),
    WATOPIA_HILLY("Watopia flat", 9.1, 100),
    WATOPIA_VOLCANO_FLAT("Watopia flat", 12.3, 46);

    private String name;

    private Double distance;

    private Integer elevation;

    ZwiftTrack(final String name, final Double distance, final Integer elevation) {
        this.name = name;
        this.distance = distance;
        this.elevation = elevation;
    }
}