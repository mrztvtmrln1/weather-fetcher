package com.example.jobs;

import com.example.enums.DeactivationReasons;
import com.example.model.User;
import com.example.model.UserDeactivationHistory;
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

    @Scheduled(cron = "0 0 * * * *")
    public void run(){
        List<User> allUsers = userService.getAllUsers();
        allUsers.forEach(u -> {
            UserDeactivationHistory userDeactivationHistory = userDeactivationHistoryService
                    .getLastUserDeactivationHistory(u.getId());
            if (userDeactivationHistory == null) {
                return;
            }
            if(userDeactivationHistory.getDeactivationReason() == DeactivationReasons.TEMP_BLOCK
                    && userDeactivationHistory.getDeactivationDate().isBefore(LocalDateTime.now().minusDays(30))){
                    log.info("UserTerminationJob started");
                    userDeactivationHistory.setDeactivationReason(DeactivationReasons.INACTIVE_BLOCK);
                    userDeactivationHistory.setDeactivationDate(LocalDateTime.now());
                    userDeactivationHistoryService.save(userDeactivationHistory);
                    log.info("User {} moved from TEMP_BLOCK to INACTIVE_BLOCK", userDeactivationHistory.getId());
            }
        });
    }
}
