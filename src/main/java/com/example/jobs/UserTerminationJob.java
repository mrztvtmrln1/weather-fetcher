package com.example.jobs;

import com.example.service.UserDeactivationHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserTerminationJob {
    UserDeactivationHistoryService userDeactivationHistoryService;

    public void run(){

    }
}
