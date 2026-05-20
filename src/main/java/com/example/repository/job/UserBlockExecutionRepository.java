package com.example.repository.job;

import com.example.model.job.UserBlockJobExecution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBlockExecutionRepository extends JpaRepository<UserBlockJobExecution, String> {
}
