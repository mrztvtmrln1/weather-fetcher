package com.example.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "first_name",  nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name",   nullable = false, length = 100)
    private String lastName;

    @Column(name = "login", nullable = false, length = 100,unique = true)
    private String login;

    @Column(name = "profile_id", nullable = false)
    private Integer profileId;
}
