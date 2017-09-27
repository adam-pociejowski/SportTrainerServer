package com.valverde.sporttrainerserver.zwift.service;

import com.valverde.sporttrainerserver.zwift.dto.RiderStateDTO;
import com.valverde.sporttrainerserver.zwift.dto.ZwiftRankingDTO;
import com.valverde.sporttrainerserver.zwift.dto.ZwiftRankingItemDTO;
import com.valverde.sporttrainerserver.zwift.entity.RiderState;
import com.valverde.sporttrainerserver.zwift.entity.ZwiftActivity;
import com.valverde.sporttrainerserver.zwift.enums.ZwiftTrack;
import com.valverde.sporttrainerserver.zwift.repository.ZwiftActivityRankingRepository;
import org.springframework.stereotype.Service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class ZwiftRankingService {

    ZwiftRankingDTO getRankingForRiderStatus(final RiderStateDTO riderStateDTO, final ZwiftActivity activity) {
        final ZwiftRankingDTO zwiftRanking = new ZwiftRankingDTO();
        final List<RiderState> riderStates = zwiftActivityRankingRepository.getRankingForDistanceAndActivity(
                        riderStateDTO.getDistance(),
                        activity.getId());
        final List<ZwiftRankingItemDTO> rankingItems = convertToRankingItems(riderStates);
        rankingItems.add(createActualRankingItem(riderStateDTO));
        sortRankingItems(rankingItems);
        zwiftRanking.setResults(rankingItems);
        zwiftRanking.setTrack(activity.getTrack());
        zwiftRanking.setDistance(riderStateDTO.getDistance());
        return zwiftRanking;
    }

    ZwiftRankingDTO getRankingForDistance(final Integer distance, final ZwiftTrack track) {
        final ZwiftRankingDTO zwiftRanking = new ZwiftRankingDTO();
        final List<RiderState> riderStates = zwiftActivityRankingRepository.getRankingForDistance(distance);
        final List<ZwiftRankingItemDTO> rankingItems = convertToRankingItems(riderStates);
        sortRankingItems(rankingItems);
        zwiftRanking.setResults(rankingItems);
        zwiftRanking.setTrack(track);
        zwiftRanking.setDistance(distance);
        return zwiftRanking;
    }

    private void sortRankingItems(final List<ZwiftRankingItemDTO> rankingItems) {
        rankingItems.sort(Comparator.comparing(ZwiftRankingItemDTO::getTime));
        int position = 1;
        final ZwiftRankingItemDTO bestResult = getBestResult(rankingItems);
        for (ZwiftRankingItemDTO zwiftRankingItem : rankingItems) {
            zwiftRankingItem.setPosition(position++);
            zwiftRankingItem.setTimeDifference(zwiftRankingItem.getTime() - bestResult.getTime());
        }
    }

    private ZwiftRankingItemDTO getBestResult(final List<ZwiftRankingItemDTO> rankingItems) {
        if (rankingItems.isEmpty())
            throw new RuntimeException("No results found");
        return rankingItems.get(0);
    }

    private ZwiftRankingItemDTO createActualRankingItem(final RiderStateDTO riderState) {
        final ZwiftRankingItemDTO rankingItem = convertToRankingItems(riderState, new Date());
        rankingItem.setActualResult(true);
        return rankingItem;
    }

    private List<ZwiftRankingItemDTO> convertToRankingItems(final List<RiderState> riderStates) {
        final List<ZwiftRankingItemDTO> rankingItems = new ArrayList<>();
        riderStates.forEach(riderState -> {
            final RiderStateDTO riderStateDTO = RiderStateDTO.toDTO(riderState);
            final Date date = riderState.getActivity().getStartDate();
            rankingItems.add(convertToRankingItems(riderStateDTO, date));
        });
        return rankingItems;
    }

    private ZwiftRankingItemDTO convertToRankingItems(final RiderStateDTO riderState,
                                                      final Date date) {
        final ZwiftRankingItemDTO rankingItem = new ZwiftRankingItemDTO();
        rankingItem.setTime(riderState.getTime());
        rankingItem.setRiderName("Adam Pociejowski");
        rankingItem.setDate(formatDate(date));
        return rankingItem;
    }

    private String formatDate(final Date date) {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public ZwiftRankingService(final ZwiftActivityRankingRepository zwiftActivityRankingRepository) {
        this.zwiftActivityRankingRepository = zwiftActivityRankingRepository;
    }

    private final ZwiftActivityRankingRepository zwiftActivityRankingRepository;
}