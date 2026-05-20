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

    private static final String ID = "merchant-termination-job-execution";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime coverage_up_to;
    private Long users;
    private LocalDateTime created_at;
    private LocalDateTime completed_at;

    public static String ID(){
        return ID;
    }
}
