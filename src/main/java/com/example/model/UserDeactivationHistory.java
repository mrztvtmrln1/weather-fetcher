package com.example.model;

import com.example.enums.DeactivationReasons;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "merchant_deactivation_history")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDeactivationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private LocalDateTime deactivationDate;
    @Enumerated(EnumType.STRING)
    private DeactivationReasons deactivationReason;
    private LocalDateTime endDate;

}
