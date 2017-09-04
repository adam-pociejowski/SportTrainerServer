package com.valverde.sporttrainerserver.statistics.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserStatsSummaryDTO {

    private Date intervalBegin;

    private Date intervalEnd;

    private List<ActivityStatsDTO> stats;
}
