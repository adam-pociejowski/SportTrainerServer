package com.valverde.sporttrainerserver.zwift.dto;

import lombok.Data;

@Data
public class ZwiftRankingItemDTO {

    private String riderName;

    private Integer time;

    private Integer position;

    private Integer timeDifference;

    private String date;

    private boolean isActualResult = false;
}
