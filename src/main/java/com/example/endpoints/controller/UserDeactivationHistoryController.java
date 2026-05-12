package com.example.endpoints.controller;

import com.example.model.UserDeactivationHistory;
import com.example.service.UserDeactivationHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-deactivation-history")
@RequiredArgsConstructor
public class UserDeactivationHistoryController {
    private final UserDeactivationHistoryService userDeactivationHistoryService;

    @PostMapping
    public UserDeactivationHistory save(@RequestBody UserDeactivationHistory userDeactivationHistory){
        return userDeactivationHistoryService.save(userDeactivationHistory);
    }
    @GetMapping
    public UserDeactivationHistory getLastUserDeactivationHistory(Long userId){
        return userDeactivationHistoryService.getLastUserDeactivationHistory(userId);
    }
}
