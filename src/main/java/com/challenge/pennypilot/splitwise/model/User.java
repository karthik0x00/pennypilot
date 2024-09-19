package com.challenge.pennypilot.splitwise.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity @Table(name = "UserTable")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String password;
    private boolean isActive;
    private boolean isConfirmed;
    private LocalDateTime createdTime = LocalDateTime.now();
    @ManyToMany(mappedBy = "users")
    private Set<Group> groups;
}

