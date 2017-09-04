package com.valverde.sporttrainerserver.statistics.dto;

import com.valverde.sporttrainerserver.statistics.enums.ResultSummaryType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SummaryDTO {

    private ResultSummaryType type;

    private Double value;
}
