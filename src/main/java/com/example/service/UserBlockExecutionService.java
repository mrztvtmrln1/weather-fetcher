package com.example.service;

import com.example.model.job.UserBlockJobExecution;
import com.example.repository.job.UserBlockExecutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserBlockExecutionService {
    public static final Long JOB_EXECUTION_ID = 1L;
    private final UserBlockExecutionRepository userBlockExecutionRepository;

    public void updateExecution(LocalDateTime coverageUpTo, int users) {
        UserBlockJobExecution execution = userBlockExecutionRepository.findById(JOB_EXECUTION_ID)
                .orElseThrow(() -> new RuntimeException("Job execution row not found"));

        execution.setCoverageUpTo(coverageUpTo);
        execution.setUsers(users);
        execution.setCompletedAt(LocalDateTime.now());

        userBlockExecutionRepository.save(execution);
    }

}
