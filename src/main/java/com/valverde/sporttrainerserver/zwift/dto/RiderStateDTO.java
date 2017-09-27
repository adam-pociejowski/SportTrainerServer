package com.valverde.sporttrainerserver.zwift.dto;

import com.valverde.sporttrainerserver.zwift.entity.RiderState;
import com.valverde.sporttrainerserver.zwift.entity.ZwiftActivity;
import com.valverde.sporttrainerserver.zwift.proto.ZwiftMessages;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class RiderStateDTO {

    private Long id;

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

    public static RiderStateDTO toDTO(@NotNull RiderState riderState) {
        return new ModelMapper().map(riderState, RiderStateDTO.class);
    }

    public static RiderState toEntity(@NotNull RiderStateDTO riderStateDTO, @NotNull ZwiftActivity activity) {
        final RiderState riderState = new ModelMapper().map(riderStateDTO, RiderState.class);
        riderState.setActivity(activity);
        return riderState;
    }
}