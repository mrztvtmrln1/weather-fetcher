package com.example.service;

import com.example.repository.job.UserBlockExecutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBlockExecutionService {
    private final UserBlockExecutionRepository userBlockExecutionRepository;

    public void save(){
        //TODO
    }
}
