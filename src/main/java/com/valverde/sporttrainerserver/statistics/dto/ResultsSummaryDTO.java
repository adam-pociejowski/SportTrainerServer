package com.valverde.sporttrainerserver.statistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultsSummaryDTO {

    private Date intervalBegin;

    private Date intervalEnd;

    private List<SummaryDTO> results;

    private Long activitiesAmount;
}