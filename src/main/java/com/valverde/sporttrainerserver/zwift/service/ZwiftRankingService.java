package com.valverde.sporttrainerserver.zwift.service;

import com.valverde.sporttrainerserver.zwift.dto.RiderStateDTO;
import com.valverde.sporttrainerserver.zwift.dto.ZwiftRankingDTO;
import com.valverde.sporttrainerserver.zwift.dto.ZwiftRankingItemDTO;
import com.valverde.sporttrainerserver.zwift.entity.RiderState;
import com.valverde.sporttrainerserver.zwift.entity.ZwiftActivity;
import com.valverde.sporttrainerserver.zwift.enums.ZwiftTrack;
import com.valverde.sporttrainerserver.zwift.repository.ZwiftActivityRankingRepository;
import com.valverde.sporttrainerserver.zwift.util.ZwiftUtils;
import org.springframework.stereotype.Service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class ZwiftRankingService {

    ZwiftRankingDTO getRankingForRiderStatus(final RiderStateDTO riderStateDTO,
                                             final ZwiftActivity activity,
                                             final ZwiftTrack track) {
        final Integer distance = riderStateDTO.getDistance();
        final ZwiftRankingDTO zwiftRanking = new ZwiftRankingDTO();
        final List<RiderState> riderStates = zwiftActivityRankingRepository.getRankingForDistanceAndActivity(
                        riderStateDTO.getDistance(),
                        track,
                        activity.getId());
        final List<ZwiftRankingItemDTO> rankingItems = convertToRankingItems(riderStates, distance);
        addActualResultToRanking(rankingItems, riderStateDTO, activity.getTrack(), track);
        sortRankingItems(rankingItems);
        zwiftRanking.setResults(rankingItems);
        zwiftRanking.setTrack(activity.getTrack());
        zwiftRanking.setDistance(riderStateDTO.getDistance());
        return zwiftRanking;
    }

    private void addActualResultToRanking(final List<ZwiftRankingItemDTO> rankingItems,
                                          final RiderStateDTO riderStateDTO,
                                          final ZwiftTrack activityTrack,
                                          final ZwiftTrack rankingTrack) {
        if (activityTrack.equals(rankingTrack) || rankingTrack.equals(ZwiftTrack.ALL)) {
            rankingItems.add(createActualRankingItem(riderStateDTO));
        }
    }

    ZwiftRankingDTO getRankingForDistance(final Integer distance, final ZwiftTrack track) {
        final ZwiftRankingDTO zwiftRanking = new ZwiftRankingDTO();
        final List<RiderState> riderStates =
                zwiftActivityRankingRepository.getRankingForDistance(distance, track);
        final List<ZwiftRankingItemDTO> rankingItems = convertToRankingItems(riderStates, distance);
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
            throw new NoZwiftResultFoundException();
        return rankingItems.get(0);
    }

    private ZwiftRankingItemDTO createActualRankingItem(final RiderStateDTO riderState) {
        final ZwiftRankingItemDTO rankingItem = convertToRankingItem(riderState, new Date());
        rankingItem.setActualResult(true);
        return rankingItem;
    }

    private List<ZwiftRankingItemDTO> convertToRankingItems(final List<RiderState> riderStates,
                                                            final Integer accurateDistance) {
        final List<ZwiftRankingItemDTO> rankingItems = new ArrayList<>();
        riderStates.forEach(riderState -> {
            final RiderStateDTO riderStateDTO = RiderStateDTO.toDTO(riderState);
            final Date date = riderState.getActivity().getStartDate();
            final ZwiftRankingItemDTO rankingItem = convertToRankingItem(riderStateDTO, date);
            rankingItem.setTime(ZwiftUtils.calculateAccurateTime(accurateDistance, riderStateDTO));
            rankingItems.add(rankingItem);
        });
        return rankingItems;
    }

    private ZwiftRankingItemDTO convertToRankingItem(final RiderStateDTO riderState,
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

    public class NoZwiftResultFoundException extends RuntimeException {}
}