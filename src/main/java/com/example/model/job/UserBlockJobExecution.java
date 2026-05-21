package com.example.model.job;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_block_job_execution")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBlockJobExecution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime coverageUpTo;
    private int users;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
}
