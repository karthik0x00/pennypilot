package com.challenge.pennypilot.splitwise.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity @Table(name = "UserTable")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    private String name;

    @Column(unique = true)
    private String emailId;

    private boolean isActive;
    private LocalDateTime createdTime = LocalDateTime.now();

    @ManyToMany(mappedBy = "users")
    private Set<Group> groups;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

