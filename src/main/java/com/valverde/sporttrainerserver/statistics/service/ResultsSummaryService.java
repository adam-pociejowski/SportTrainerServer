package com.valverde.sporttrainerserver.statistics.service;

import com.valverde.sporttrainerserver.statistics.dto.ResultsSummaryDTO;
import com.valverde.sporttrainerserver.statistics.dto.SummaryDTO;
import com.valverde.sporttrainerserver.statistics.repository.ResultsSummaryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.valverde.sporttrainerserver.statistics.enums.ResultSummaryType.CALORIES;
import static com.valverde.sporttrainerserver.statistics.enums.ResultSummaryType.DISTANCE;
import static com.valverde.sporttrainerserver.statistics.enums.ResultSummaryType.TIME;

@Service
@Transactional
public class ResultsSummaryService {

    public ResultsSummaryDTO findResultsSummary(final String username, final Date intervalBegin, final Date intervalEnd) {
        final List<SummaryDTO> summariesList = resultsSummaryRepository.getSummary(username, intervalBegin, intervalEnd,
                Arrays.asList(DISTANCE, TIME, CALORIES));
        final Long activitiesCount = resultsSummaryRepository.getActivitiesCount(username, intervalBegin, intervalEnd);
        return new ResultsSummaryDTO(intervalBegin, intervalEnd, summariesList, activitiesCount);
    }

    public ResultsSummaryService(ResultsSummaryRepository resultsSummaryRepository) {
        this.resultsSummaryRepository = resultsSummaryRepository;
    }

    private final ResultsSummaryRepository resultsSummaryRepository;
}
