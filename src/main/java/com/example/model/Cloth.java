package com.example.model;

import com.example.enums.ClothSeasonType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clothes")
public class Cloth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clothName;

    @Enumerated(EnumType.STRING)
    private ClothSeasonType seasonType;
}
