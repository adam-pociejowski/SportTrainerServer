package com.valverde.sporttrainerserver.results.dto;

import com.valverde.sporttrainerserver.results.enums.ResultSummaryType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SummaryDTO {

    private ResultSummaryType type;

    private Double value;
}
