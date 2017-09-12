package com.valverde.sporttrainerserver.activity.repository;

import com.valverde.sporttrainerserver.activity.entity.Activity;
import com.valverde.sporttrainerserver.activity.enums.ActivityType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query("SELECT a FROM Activity a INNER JOIN a.user u WHERE u.username = :username")
    Page<Activity> findAllByUsername(@Param("username") String username, Pageable pageable);

    @Query("SELECT a FROM Activity a INNER JOIN a.user u WHERE u.username = :username AND a.type = :activityType")
    Page<Activity> findAllByUsernameAndType(@Param("username") String username,
                                            @Param("activityType") ActivityType activityType,
                                            Pageable pageable);
}
