package com.valverde.sporttrainerserver.statistics.repository;

import com.valverde.sporttrainerserver.activity.entity.Activity;
import com.valverde.sporttrainerserver.statistics.dto.SummaryDTO;
import com.valverde.sporttrainerserver.statistics.enums.ResultSummaryType;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CommonsLog
@Component
public class ResultsSummaryRepository {

    public List<SummaryDTO> getSummary(final String username,
                                       final Date intervalBegin,
                                       final Date intervalEnd,
                                       final List<ResultSummaryType> types) {
        List<SummaryDTO> summaryList = new ArrayList<>();
        types.forEach(type ->
                addToListIfNotNull(findSummary(type, username, intervalBegin, intervalEnd), summaryList));
        return summaryList;
    }

    public Long getActivitiesCount(String username, Date intervalBegin, Date intervalEnd) {
        EntityManager em = createEntityManager(Activity.class);
        final String queryString = createActivitiesCountQuery(intervalBegin, intervalEnd);
        TypedQuery query = em.createQuery(queryString, Long.class);
        try {
            setParams(username, intervalBegin, intervalEnd, query);
            return (Long)query.getSingleResult();
        } catch (Exception e) {
            log.warn("Problem while getting info about activities count.", e);
        } finally {
            em.close();
        }
        return 0L;
    }

    private void setParams(String username, Date intervalBegin, Date intervalEnd, TypedQuery query) {
        query.setParameter("username", username);
        if (intervalBegin != null) {
            query.setParameter("intervalBegin", intervalBegin);
        }
        if (intervalBegin != null && intervalEnd != null) {
            query.setParameter("intervalEnd", intervalEnd);
        }
    }

    private String createActivitiesCountQuery(Date intervalBegin, Date intervalEnd) {
        return "SELECT COUNT(a.id) FROM Activity a " +
        "JOIN a.user u WHERE u.username = :username " +
                getIntervalClause(intervalBegin, intervalEnd);
    }

    private void addToListIfNotNull(SummaryDTO summary, List<SummaryDTO> summaryList) {
        if (summary != null)
            summaryList.add(summary);
    }

    private SummaryDTO findSummary(ResultSummaryType type, String username,
                                   Date intervalBegin, Date intervalEnd) {
        String queryString = createQueryForSummary(type, intervalBegin, intervalEnd);
        EntityManager em = createEntityManager(Activity.class);
        TypedQuery query;
        if (type.equals(ResultSummaryType.DISTANCE)) query = em.createQuery(queryString, Double.class);
        else query = em.createQuery(queryString, Long.class);
        try {
            setParams(username, intervalBegin, intervalEnd, query);
            Object obj = query.getSingleResult();
            Double result;
            if (obj == null) {
                return null;
            } else if (obj instanceof Double) {
                result = (Double) obj;
            } else {
                result = ((Long) obj).doubleValue();
            }
            return new SummaryDTO(type, result);
        } catch (Exception e) {
            log.warn("Problem while getting info about summary: "+type, e);
        } finally {
            em.close();
        }
        return null;
    }

    private String createQueryForSummary(ResultSummaryType type, Date intervalBegin, Date intervalEnd) {
        return "SELECT "+getSelectSection(type)+" FROM Activity a " +
                "JOIN a.user u WHERE u.username = :username " +
                getIntervalClause(intervalBegin, intervalEnd);
    }

    private String getSelectSection(ResultSummaryType type) {
        if (type.equals(ResultSummaryType.DISTANCE)) {
            return "SUM(a.distance)";
        } else if (type.equals(ResultSummaryType.TIME)) {
            return "SUM(a.totalTime)";
        } else if (type.equals(ResultSummaryType.CALORIES)) {
            return "SUM(a.calories)";
        }
        throw new RuntimeException("ResultSummaryType not found.");
    }

    private String getIntervalClause(Date intervalBegin, Date intervalEnd) {
        String query = "";
        if (intervalBegin != null && intervalEnd == null) {
            query = "AND a.date >= :intervalBegin ";
        } else if (intervalBegin != null) {
            query = "AND a.date >= :intervalBegin AND a.date <= :intervalEnd ";
        }
        return query;
    }

    private EntityManager createEntityManager(Class clazz) {
        return jpaContext.getEntityManagerByManagedType(clazz)
                .getEntityManagerFactory()
                .createEntityManager();
    }


    public ResultsSummaryRepository(final JpaContext jpaContext) {
        this.jpaContext = jpaContext;
    }

    private final JpaContext jpaContext;
}
