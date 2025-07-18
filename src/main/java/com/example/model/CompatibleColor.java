package com.example.model;

import com.example.enums.ClothColors;
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
    @Enumerated(EnumType.STRING)
    private ClothColors colorOne;

    @Enumerated(EnumType.STRING)
    private ClothColors colorTwo;
}
