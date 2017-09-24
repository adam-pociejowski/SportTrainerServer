package com.valverde.sporttrainerserver.zwift.dto;

import com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages;
import lombok.Data;

@Data
public class RiderStateDTO {

    private Integer riderId;

    private Integer roadPosition;

    private Integer time;

    private Double speed;

    private Integer distance;

    private Integer power;

    private Integer climbing;

    private Double altitude;

    private Double x;

    private Double y;

    public RiderStateDTO(final ZwiftMessages.PlayerState playerState) {
        this.x = (double) playerState.getX();
        this.y = (double) playerState.getY();
        this.riderId = playerState.getId();
        this.time = playerState.getTime();
        this.power = playerState.getPower();
        this.climbing = playerState.getClimbing();
        this.distance = playerState.getDistance();
        this.speed = (double) playerState.getSpeed();
        this.altitude = (double) playerState.getAltitude();
        this.roadPosition = playerState.getRoadPosition();
    }
}