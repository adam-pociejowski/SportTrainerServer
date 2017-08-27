package com.valverde.sporttrainerserver.results.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class ResultsSummaryDTO {

    private Date intervalBegin;

    private Date intervalEnd;

    private List<SummaryDTO> results;

    private Long activitiesAmount;
}