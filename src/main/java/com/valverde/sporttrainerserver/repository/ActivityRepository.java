package com.valverde.sporttrainerserver.repository;

import com.valverde.sporttrainerserver.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query("SELECT a FROM Activity a INNER JOIN a.user u WHERE u.username = :username")
    Page<Activity> findAllByUsername(@Param("username") String username, Pageable pageable);
}
