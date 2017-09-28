package com.valverde.sporttrainerserver.zwift.enums;

import lombok.Getter;
import static com.valverde.sporttrainerserver.zwift.enums.ZwiftTrackType.*;

@Getter
public enum ZwiftTrack {
    ALL("All", 0.0, 0, null),
    WATOPIA_FLAT("Watopia flat", 10.3, 54, FLAT),
    WATOPIA_HILLY("Watopia hilly", 9.1, 100, HILLY),
    WATOPIA_VOLCANO_FLAT("Watopia volcano flat", 12.3, 46, FLAT),
    WATOPIA_FIGURE_8("Watopia figure 8", 29.8, 234, HILLY),
    WATOPIA_MOUNTAIN("Watopia mountain", 29.5, 671, MOUNTAIN),
    WATOPIA_MOUNTAIN_8("Watopia mountain 8", 32.0, 677, MOUNTAIN),
    WATOPIA_THE_PRETZEL("Watopia the pretzel", 72.2, 1333, MOUNTAIN),
    WATOPIA_THREE_SISTERS("Watopia three sister", 47.8, 879, HILLY),
    WATOPIA_VOLCANO_CIRCUIT("Watopia volcano circuit", 4.0, 17, FLAT),
    WATOPIA_VOLCANO_CIRCUIT_CCW("Watopia volcano circuit CCW", 4.1, 17, FLAT),
    RICHMOND_FULL_COURSE("Richmond full course", 16.3, 142, HILLY),
    RICHMOND_HILLY("Richmond hilly", 9.2, 124, HILLY),
    RICHMOND_FLAT("Richmond flat", 5.1, 11, FLAT),
    LONDON_CLASSIC("London classic", 5.4, 19, FLAT),
    LONDON_8("London 8", 20.3, 238, MOUNTAIN),
    GREATER_LONDON_8("Greater London 8", 23.8, 256, MOUNTAIN),
    GREATER_LONDON_FLAT("Greater London flat", 11.6, 45, FLAT),
    LONDON_LOOP("London loop", 14.9, 220, MOUNTAIN),
    GREATER_LONDON_LOOP("Greater London loop", 21.0, 244, MOUNTAIN),
    LONDON_PRETZEL("London pretzel", 55.6, 531, HILLY),
    LONDON_PRL_HALF("London PRL half", 69.0, 954, MOUNTAIN),
    LONDON_PRL_FULL("London PRL full", 173.0, 2496, MOUNTAIN);

    private String name;

    private Double distance;

    private Integer elevation;

    private ZwiftTrackType trackType;

    ZwiftTrack(final String name,
               final Double distance,
               final Integer elevation,
               final ZwiftTrackType trackType) {
        this.name = name;
        this.distance = distance;
        this.elevation = elevation;
        this.trackType = trackType;
    }
}