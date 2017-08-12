package com.valverde.sporttrainerserver.activity.repository;

import com.valverde.sporttrainerserver.activity.entity.Activity;
import com.valverde.sporttrainerserver.activity.entity.ActivityRecord;
import com.valverde.sporttrainerserver.activity.enums.RecordMeasureType;
import com.valverde.sporttrainerserver.activity.enums.RecordType;
import com.valverde.sporttrainerserver.activity.util.ActivityUtils;
import com.valverde.sporttrainerserver.activity.dto.ActivityRecordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ActivityRecordsRepository {

    public List<ActivityRecordDTO> findRecordsForInterval(@NotNull String username, Date intervalBegin, Date intervalEnd) {
        List<ActivityRecordDTO> recordsList = new ArrayList<>();
        this.addToListIfNotNull(this.findRecord(username, RecordType.ONE_KILOMETER, intervalBegin, intervalEnd), recordsList);
        this.addToListIfNotNull(this.findRecord(username, RecordType.TWO_KILOMETERS, intervalBegin, intervalEnd), recordsList);
        this.addToListIfNotNull(this.findRecord(username, RecordType.THREE_KILOMETERS, intervalBegin, intervalEnd), recordsList);
        this.addToListIfNotNull(this.findRecord(username, RecordType.FIVE_KILOMETERS, intervalBegin, intervalEnd), recordsList);
        this.addToListIfNotNull(this.findRecord(username, RecordType.COPPER_TEST, intervalBegin, intervalEnd), recordsList);
        this.addToListIfDTONotNull(this.findBestActivityRecord(username, RecordType.LONGEST_DISTANCE, intervalBegin, intervalEnd), recordsList);
        this.addToListIfDTONotNull(this.findBestActivityRecord(username, RecordType.LONGEST_TIME, intervalBegin, intervalEnd), recordsList);
        this.addToListIfDTONotNull(this.findBestActivityRecord(username, RecordType.BEST_AVG_SPEED, intervalBegin, intervalEnd), recordsList);
        return recordsList;
    }

    private void addToListIfNotNull(ActivityRecord activityRecord, List<ActivityRecordDTO> recordsList) {
        if (activityRecord != null)
            recordsList.add(ActivityRecordDTO.toDTO(activityRecord));
    }

    private void addToListIfDTONotNull(ActivityRecordDTO activityRecord, List<ActivityRecordDTO> recordsList) {
        if (activityRecord != null) {
            recordsList.add(activityRecord);
        }
    }

    private ActivityRecord findRecord(@NotNull String username,
                                     @NotNull RecordType recordType,
                                     Date intervalBegin,
                                     Date intervalEnd) {
        String queryString = createUserRecordsQueryString(intervalBegin, intervalEnd, recordType);
        EntityManager em = createEntityManager(ActivityRecord.class);
        TypedQuery query = em.createQuery(queryString, ActivityRecord.class);
        try {
            query.setParameter("username", username);
            query.setParameter("recordType", recordType);
            if (intervalBegin != null) {
                query.setParameter("intervalBegin", intervalBegin);
            }
            if (intervalBegin != null && intervalEnd != null) {
                query.setParameter("intervalEnd", intervalEnd);
            }
            query.setMaxResults(1);
            ActivityRecord activityRecord = (ActivityRecord)query.getSingleResult();
            if (activityRecord != null) {
                return activityRecord;
            }
        } catch (Exception e) {}
        finally {
            em.close();
        }
        return null;
    }

    private String createUserRecordsQueryString(Date intervalBegin, Date intervalEnd, RecordType recordType) {
        return "SELECT ar FROM ActivityRecord ar JOIN FETCH ar.activity a " +
                "JOIN a.user u WHERE u.username = :username AND ar.type = :recordType " +
                this.getIntervalClause(intervalBegin, intervalEnd) +
                "ORDER BY ar.value " + this.getSortType(recordType);
    }

    private ActivityRecordDTO findBestActivityRecord(@NotNull String username, @NotNull RecordType recordType,
                                                    Date intervalBegin, Date intervalEnd) {
        String queryString = createLongestDistanceQueryString(recordType, intervalBegin, intervalEnd);
        EntityManager em = createEntityManager(Activity.class);
        try {
            TypedQuery query = em.createQuery(queryString, Activity.class);
            query.setParameter("username", username);
            if (intervalBegin != null) {
                query.setParameter("intervalBegin", intervalBegin);
            }
            if (intervalBegin != null && intervalEnd != null) {
                query.setParameter("intervalEnd", intervalEnd);
            }
            query.setMaxResults(1);
            Activity activity = (Activity)query.getSingleResult();
            if (activity != null) {
                ActivityRecordDTO recordDTO = new ActivityRecordDTO();
                recordDTO.setActivityId(activity.getId());
                switch(recordType) {
                    case LONGEST_DISTANCE:
                        recordDTO.setType(RecordType.LONGEST_DISTANCE);
                        recordDTO.setValue(activity.getDistance());
                        return recordDTO;
                    case LONGEST_TIME:
                        recordDTO.setType(RecordType.LONGEST_TIME);
                        recordDTO.setValue((double)activity.getTotalTime());
                        return recordDTO;
                    case BEST_AVG_SPEED:
                        recordDTO.setType(RecordType.BEST_AVG_SPEED);
                        recordDTO.setValue(ActivityUtils.getAvgSpeed(activity));
                        return recordDTO;
                    default:
                        throw new RuntimeException("Record type not found");
                }
            }
        } catch (Exception e) {}
        finally {
            em.close();
        }
        return null;
    }

    private String createLongestDistanceQueryString(RecordType recordType, Date intervalBegin,
                                                          Date intervalEnd) {
        return "SELECT a FROM Activity a JOIN a.user u WHERE u.username = :username " +
                this.getIntervalClause(intervalBegin, intervalEnd) + "ORDER BY " + this.getOrderByClause(recordType);
    }

    private String getOrderByClause(RecordType recordType) {
        switch(recordType) {
            case LONGEST_DISTANCE:
                return "a.distance DESC";
            case LONGEST_TIME:
                return "a.totalTime DESC";
            case BEST_AVG_SPEED:
                return "(a.distance  / a.totalTime) DESC";
            default:
                return "";
        }
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

    private String getSortType(RecordType recordType) {
        if (recordType.getMeasureType().equals(RecordMeasureType.DISTANCE))
            return "ASC";
        return "DESC";
    }

    private EntityManager createEntityManager(Class clazz) {
        return jpaContext.getEntityManagerByManagedType(clazz)
                .getEntityManagerFactory()
                .createEntityManager();
    }

    @Autowired
    public ActivityRecordsRepository(final JpaContext jpaContext) {
        this.jpaContext = jpaContext;
    }

    private final JpaContext jpaContext;
}