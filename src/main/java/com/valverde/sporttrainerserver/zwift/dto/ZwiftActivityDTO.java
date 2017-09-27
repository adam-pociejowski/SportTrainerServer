package com.valverde.sporttrainerserver.zwift.dto;

import com.valverde.sporttrainerserver.zwift.entity.ZwiftActivity;
import com.valverde.sporttrainerserver.zwift.enums.ZwiftTrack;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class ZwiftActivityDTO {

    private Long id;

    private List<RiderStateDTO> states;

    private ZwiftTrack track;

    private Date startDate;

    private String riderId;

    public static ZwiftActivityDTO toDTO(@NotNull ZwiftActivity activity) {
        return new ModelMapper().map(activity, ZwiftActivityDTO.class);
    }

    public static ZwiftActivity toEntity(@NotNull ZwiftActivityDTO activityDTO) {
        ZwiftActivity activity = new ModelMapper().map(activityDTO, ZwiftActivity.class);
        activity.getStates().forEach(state -> state.setActivity(activity));
        return activity;
    }
}