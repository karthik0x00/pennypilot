package com.challenge.pennypilot.splitwise.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity @Table(name = "GroupTable")
public class Group {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long groupID;

    private String name;
    private String description;
    private boolean isActive;
    private LocalDateTime createdTime = LocalDateTime.now();
    @OneToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToMany
    @JoinTable (
        name = "UserGroupMapping",
        joinColumns = @JoinColumn(name = "group_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;
}
