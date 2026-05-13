package com.example.service;

import com.example.model.UserDeactivationHistory;
import com.example.repository.UserDeactivationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDeactivationHistoryService {
    private final UserDeactivationHistoryRepository userDeactivationHistoryRepository;

    public UserDeactivationHistory getLastUserDeactivationHistory(Long userId) {
        return userDeactivationHistoryRepository.findTopByUserIdOrderByDeactivationDateDesc(userId);
    }

    public UserDeactivationHistory save(UserDeactivationHistory userDeactivationHistory) {
        return userDeactivationHistoryRepository.save(userDeactivationHistory);
    }
}
