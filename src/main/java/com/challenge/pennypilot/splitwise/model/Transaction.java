package com.challenge.pennypilot.splitwise.model;

import jakarta.persistence.*;
import com.challenge.pennypilot.splitwise.SplitStrategy;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    @OneToOne
    @JoinColumn(name = "paid_by")
    private User paidBy;

    private SplitStrategy splitStrategy;
    private LocalDateTime createdTime = LocalDateTime.now();

    @OneToMany(mappedBy = "transaction")
    private Set<TransactionSplitMap> transactionSplitMap;

    private double amount;
}
