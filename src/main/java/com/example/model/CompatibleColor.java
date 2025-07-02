package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "compatible_colors")
@NoArgsConstructor
@AllArgsConstructor
public class CompatibleColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "color_one", nullable = false)
    private String colorOne;

    @Column(name = "color_two", nullable = false)
    private String colorTwo;
}
