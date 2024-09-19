package com.challenge.pennypilot.splitwise.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record TransactionSplitMapID(@Column(name = "user_id") long userId, @Column(name = "transaction_id") long transactionId) {}
