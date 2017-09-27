package com.valverde.sporttrainerserver.zwift.repository;

import com.valverde.sporttrainerserver.base.util.AppUtils;
import com.valverde.sporttrainerserver.zwift.entity.RiderState;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class ZwiftActivityRankingRepository {

    public List<RiderState> getRankingForDistance(final Integer distance) {
        return getNearestRiderStates(distance, null);
    }

    public List<RiderState> getRankingForDistanceAndActivity(final Integer distance,
                                                             final Long activityId) {
        return getNearestRiderStates(distance, activityId);
    }

    private List<RiderState> getNearestRiderStates(final Integer distance, final Long activityId) {
        List<RiderState> riderStates;
        EntityManager em = createEntityManager(RiderState.class);
        String getNearestStatesQuery = createNativeGetNearestStatesQuery(distance, activityId);
        try {
            Query query = em.createNativeQuery(getNearestStatesQuery, RiderState.class);
            riderStates = query.getResultList();
        } finally {
            em.close();
        }
        return riderStates;
    }

    private String createNativeGetNearestStatesQuery(final Integer distance, final Long activityId) {
        String queryString = "SELECT s.* FROM rider_state s " +
                "JOIN zwift_activity a ON a.id = s.activity_id "+
                "WHERE s.id IN (SELECT s1.id FROM rider_state s1 " +
                "WHERE ABS(s1.distance - "+distance+") <= "+ RESULT_DISTANCE_INTERVAL +" "+
                "AND s1.activity_id = a.id " +
                "ORDER BY ABS(s1.distance - "+distance+") " +
                "LIMIT 1)";
        if (AppUtils.isNotNull(activityId)) {
            queryString += " AND a.id <> "+activityId;
        }
        return queryString;
    }

    private EntityManager createEntityManager(Class clazz) {
        return jpaContext.getEntityManagerByManagedType(clazz)
                .getEntityManagerFactory()
                .createEntityManager();
    }

    public ZwiftActivityRankingRepository(final JpaContext jpaContext) {
        this.jpaContext = jpaContext;
    }

    private final static Integer RESULT_DISTANCE_INTERVAL = 20;

    private final JpaContext jpaContext;
}