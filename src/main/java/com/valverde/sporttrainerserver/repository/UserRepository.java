package com.valverde.sporttrainerserver.repository;

import com.valverde.sporttrainerserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
