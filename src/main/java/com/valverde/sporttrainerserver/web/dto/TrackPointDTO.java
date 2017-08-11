package com.valverde.sporttrainerserver.web.dto;

import lombok.Data;

@Data
public class TrackPointDTO {

    private Double latitude;

    private Double longitude;

    private Double distance;

    private Double altitude;

    private Long time;
}
