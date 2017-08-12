package com.valverde.sporttrainerserver.results.service;

import com.valverde.sporttrainerserver.results.dto.ResultsSummaryDTO;
import com.valverde.sporttrainerserver.results.dto.SummaryDTO;
import com.valverde.sporttrainerserver.results.repository.ResultsSummaryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ResultsSummaryService {

    public ResultsSummaryDTO findResultsSummary(String username, Date intervalBegin, Date intervalEnd) {
        final List<SummaryDTO> summariesList = resultsSummaryRepository.getSummary(username, intervalBegin, intervalEnd);
        return new ResultsSummaryDTO(intervalBegin, intervalEnd, summariesList);
    }

    public ResultsSummaryService(ResultsSummaryRepository resultsSummaryRepository) {
        this.resultsSummaryRepository = resultsSummaryRepository;
    }

    private final ResultsSummaryRepository resultsSummaryRepository;
}
