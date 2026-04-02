package com.example.model;

import com.example.enums.DeactivationReasons;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "merchant_deactivation_history")
@NoArgsConstructor
@AllArgsConstructor
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
