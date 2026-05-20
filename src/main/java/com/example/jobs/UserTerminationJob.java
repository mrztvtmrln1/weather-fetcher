package com.example.jobs;

import com.example.enums.UserStatus;
import com.example.model.UserDeactivationHistory;
import com.example.model.job.UserBlockJobExecution;
import com.example.repository.UserDeactivationHistoryRepository;
import com.example.repository.job.UserBlockExecutionRepository;
import com.example.service.UserBlockExecutionService;
import com.example.service.UserDeactivationHistoryService;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserTerminationJob {
    private final UserDeactivationHistoryService userDeactivationHistoryService;
    private final UserService userService;
    private final UserBlockExecutionRepository userBlockExecutionRepository;
    private final UserDeactivationHistoryRepository  userDeactivationHistoryRepository;
    private final UserBlockExecutionService userBlockExecutionService;

    @Scheduled(cron = "0 0 * * * *")
    public void run(){

        LocalDateTime jobStartTime = LocalDateTime.now();

        LocalDateTime from = userBlockExecutionRepository
                .findById(UserBlockJobExecution.ID())
                .map(UserBlockJobExecution::getCoverage_up_to)
                .orElse(LocalDateTime.now().minusDays(30));

        LocalDateTime to = LocalDateTime.now().minusDays(30).plusHours(1);

        List<UserDeactivationHistory> allUsersToTerminate = getAllUsersToTerminate(from, to);

        allUsersToTerminate.forEach(userDeactivationHistory -> {
            userService.changeStatus(userDeactivationHistory.getUserId(), UserStatus.BLOCKED);
            userDeactivationHistoryRepository.save(userDeactivationHistory);
            //TODO

        });
    }

    public List<UserDeactivationHistory> getAllUsersToTerminate(LocalDateTime from, LocalDateTime to){
        return userDeactivationHistoryRepository.findAllUsersToTerminateInRange(from, to);
    }

}
