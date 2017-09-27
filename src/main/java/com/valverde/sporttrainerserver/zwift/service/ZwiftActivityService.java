package com.valverde.sporttrainerserver.zwift.service;

import com.valverde.sporttrainerserver.base.enums.State;
import com.valverde.sporttrainerserver.zwift.dto.RiderStateDTO;
import com.valverde.sporttrainerserver.zwift.dto.ZwiftRankingDTO;
import com.valverde.sporttrainerserver.zwift.dto.ZwiftStartActivityDTO;
import com.valverde.sporttrainerserver.zwift.entity.RiderState;
import com.valverde.sporttrainerserver.zwift.entity.ZwiftActivity;
import com.valverde.sporttrainerserver.zwift.enums.ZwiftTrack;
import com.valverde.sporttrainerserver.zwift.repository.ZwiftActivityRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

@Service
@CommonsLog
@Transactional
public class ZwiftActivityService {

    public Long startActivity(final ZwiftStartActivityDTO zwiftStartActivity) {
        final ZwiftActivity activity = new ZwiftActivity();
        activity.setTrack(zwiftStartActivity.getTrack());
        activity.setRiderId(zwiftStartActivity.getRiderId());
        activity.setActivityState(State.TRIGGERED);
        zwiftActivityRepository.save(activity);
        log.info("Zwift activity started recording. ActivityId: "+activity.getId());
        return activity.getId();
    }

    public ZwiftRankingDTO getStatsForActivity(final Long activityId) throws Exception {
        final ZwiftActivity activity = zwiftActivityRepository.findOne(activityId);
        final RiderStateDTO riderStatusDTO = zwiftRiderStatusService.getRiderStatus(activity.getRiderId());
        if (isActivityStarted(riderStatusDTO)) {
            if (activity.getActivityState().equals(State.TRIGGERED)) {
                activity.setStartDate(new Date());
                activity.setActivityState(State.STARTED);
            }
            final RiderState riderState = RiderStateDTO.toEntity(riderStatusDTO, activity);
            activity.getStates().add(riderState);
            zwiftActivityRepository.save(activity);
        }
        ZwiftRankingDTO ranking = zwiftRankingService.getRankingForRiderStatus(
                riderStatusDTO,
                activity);
        log.info("Zwift ranking refreshed. Stats amount: "+ranking.getResults().size());
        return ranking;
    }

    public ZwiftRankingDTO getTrackRanking(final Integer distance) {
        return zwiftRankingService.getRankingForDistance(distance, ZwiftTrack.WATOPIA_VOLCANO_FLAT);
    }

    public void finishActivity(Long activityId) {
        final ZwiftActivity activity = zwiftActivityRepository.findOne(activityId);
        activity.setActivityState(State.FINISHED);
        zwiftActivityRepository.save(activity);
        log.info("Zwift activity finished recording. ActivityId: "+activity.getId());
    }

    private boolean isActivityStarted(final RiderStateDTO riderState) {
        return riderState.getDistance() > 0.0;
    }

    public ZwiftActivityService(final ZwiftRiderStatusService zwiftRiderStatusService,
                                final ZwiftActivityRepository zwiftActivityRepository,
                                final ZwiftRankingService zwiftRankingService) {
        this.zwiftRiderStatusService = zwiftRiderStatusService;
        this.zwiftActivityRepository = zwiftActivityRepository;
        this.zwiftRankingService = zwiftRankingService;
    }

    private final ZwiftRiderStatusService zwiftRiderStatusService;

    private final ZwiftActivityRepository zwiftActivityRepository;

    private final ZwiftRankingService zwiftRankingService;
}