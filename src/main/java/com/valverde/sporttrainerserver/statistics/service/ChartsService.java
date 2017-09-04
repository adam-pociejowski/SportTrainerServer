package com.valverde.sporttrainerserver.statistics.service;

import com.valverde.sporttrainerserver.statistics.dto.ResultsSummaryDTO;
import com.valverde.sporttrainerserver.statistics.enums.ResultSummaryType;
import com.valverde.sporttrainerserver.statistics.repository.ResultsSummaryRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ChartsService {

    @NotNull
    public List<ResultsSummaryDTO> generateStatsForInterval(final String username,
                                                            final Integer interval,
                                                            final Integer amount,
                                                            final ResultSummaryType type) {
        final List<DateSplit> dateSplits = generateDateSplits(amount, interval, new Date());
        final List<ResultsSummaryDTO> statsSummaries = new ArrayList<>();
        for (DateSplit split : dateSplits) {
            ResultsSummaryDTO statsSummary = new ResultsSummaryDTO();
            statsSummary.setIntervalBegin(split.start);
            statsSummary.setIntervalEnd(split.end);
            statsSummary.setResults(
                    summaryRepository.getSummary(username, split.start, split.end, Collections.singletonList(type)));
        }
        return statsSummaries;
    }

    @NotNull
    private List<DateSplit> generateDateSplits(final Integer amount,
                                               final Integer interval,
                                               final Date endDate) {
        final List<DateSplit> splits = new ArrayList<>();
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        for (int i = 0; i < amount; i++) {
            splits.add(generateDateSplit(interval, calendar));
        }
        return splits;
    }

    @NotNull
    private DateSplit generateDateSplit(Integer interval, Calendar calendar) {
        final Date endInterval = calendar.getTime();
        calendar.add(Calendar.DATE, -1*(interval - 1));
        final Date startInterval = calendar.getTime();
        calendar.add(Calendar.DATE, -1);
        return new DateSplit(startInterval, endInterval);
    }

    @AllArgsConstructor
    private class DateSplit {
        private Date start;
        private Date end;
    }

    public ChartsService(ResultsSummaryRepository summaryRepository) {
        this.summaryRepository = summaryRepository;
    }

    private final ResultsSummaryRepository summaryRepository;
}