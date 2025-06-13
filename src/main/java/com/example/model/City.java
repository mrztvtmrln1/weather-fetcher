package com.example.model;

import lombok.*;
import jakarta.persistence.*;


@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@ToString
@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true,length = 100)
    private String name;

    public City(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
