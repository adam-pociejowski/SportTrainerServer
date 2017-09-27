package com.valverde.sporttrainerserver.zwift.repository;

import com.valverde.sporttrainerserver.zwift.entity.ZwiftActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZwiftActivityRepository extends JpaRepository<ZwiftActivity, Long> {}