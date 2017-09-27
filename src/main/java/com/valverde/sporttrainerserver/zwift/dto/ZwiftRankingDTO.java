package com.valverde.sporttrainerserver.zwift.dto;

import com.valverde.sporttrainerserver.zwift.enums.ZwiftTrack;
import lombok.Data;
import java.util.List;

@Data
public class ZwiftRankingDTO {
    private ZwiftTrack track;

    private Integer distance;

    private List<ZwiftRankingItemDTO> results;
}