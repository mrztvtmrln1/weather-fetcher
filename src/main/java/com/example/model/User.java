package com.example.model;

import com.example.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

//@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatus status;

    public User(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public UserStatus getStatus() {
        return status;
    }
}
