package com.valverde.sporttrainerserver.activity.dto;

import lombok.Data;

@Data
public class TrackPointDTO {

    private Double latitude;

    private Double longitude;

    private Double distance;

    private Double altitude;

    private Long time;
}
