package com.example.repository;

import com.example.model.User;
import com.example.model.UserDeactivationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDeactivationHistoryRepository extends JpaRepository<UserDeactivationHistory, Long> {
    UserDeactivationHistory findTopByUserIdOrderByDeactivationDateDesc(Long userId);
}
