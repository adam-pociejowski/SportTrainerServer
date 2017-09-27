package com.valverde.sporttrainerserver.zwift.dto;

import com.valverde.sporttrainerserver.zwift.enums.ZwiftTrack;
import lombok.Data;

@Data
public class ZwiftStartActivityDTO {

    private ZwiftTrack track;

    private String riderId;
}