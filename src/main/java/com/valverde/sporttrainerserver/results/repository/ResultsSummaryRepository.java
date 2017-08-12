package com.valverde.sporttrainerserver.results.repository;

import com.valverde.sporttrainerserver.activity.entity.Activity;
import com.valverde.sporttrainerserver.results.dto.SummaryDTO;
import com.valverde.sporttrainerserver.results.enums.ResultSummaryType;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<SummaryDTO> getSummary(String username, Date intervalBegin, Date intervalEnd) {
        List<SummaryDTO> summaryList = new ArrayList<>();
        addToListIfNotNull(findSummary(ResultSummaryType.DISTANCE, username, intervalBegin, intervalEnd), summaryList);
        addToListIfNotNull(findSummary(ResultSummaryType.TIME, username, intervalBegin, intervalEnd), summaryList);
        return summaryList;
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
            query.setParameter("username", username);
            if (intervalBegin != null) {
                query.setParameter("intervalBegin", intervalBegin);
            }
            if (intervalBegin != null && intervalEnd != null) {
                query.setParameter("intervalEnd", intervalEnd);
            }
            Object obj = query.getSingleResult();
            Double result;
            if (obj instanceof Double)  result = (Double) obj;
            else result = ((Long) obj).doubleValue();
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
