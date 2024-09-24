package com.challenge.pennypilot.splitwise.model;

import jakarta.persistence.*;

@Entity
public class TransactionSplitMap {
    @EmbeddedId
    private TransactionSplitMapID transactionSplitMapId;

    @ManyToOne
    @MapsId("transactionId")
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

//    Can be amount in case of equal, custom
//    Can be percentage in case of percent
//    Can be a number in case of share
    private long splitValue;

    public TransactionSplitMapID getTransactionSplitMapId() {
        return transactionSplitMapId;
    }

    public void setTransactionSplitMapId(TransactionSplitMapID transactionSplitMapId) {
        this.transactionSplitMapId = transactionSplitMapId;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public long getSplitValue() {
        return splitValue;
    }

    public void setSplitValue(long splitValue) {
        this.splitValue = splitValue;
    }
}
