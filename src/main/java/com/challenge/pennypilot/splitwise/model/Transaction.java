package com.challenge.pennypilot.splitwise.model;

import com.challenge.pennypilot.splitwise.SplitStrategy;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    private String description;

    private long paidBy;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    private SplitStrategy splitStrategy;
    private LocalDateTime createdTime = LocalDateTime.now();

    @OneToMany(mappedBy = "transaction")
    private Set<TransactionSplitMap> transactionSplitMap;

    private double amount;

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public long getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(long paidBy) {
        this.paidBy = paidBy;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public SplitStrategy getSplitStrategy() {
        return splitStrategy;
    }

    public void setSplitStrategy(SplitStrategy splitStrategy) {
        this.splitStrategy = splitStrategy;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Set<TransactionSplitMap> getTransactionSplitMap() {
        return transactionSplitMap;
    }

    public void setTransactionSplitMap(Set<TransactionSplitMap> transactionSplitMap) {
        this.transactionSplitMap = transactionSplitMap;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
