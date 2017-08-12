package com.valverde.sporttrainerserver.base.repository;

import com.valverde.sporttrainerserver.base.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
