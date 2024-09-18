package com.challenge.pennypilot.splitwise;

import java.util.Map;

public class Transaction {
    private String description;
    private long paidBy;
    private double amount;
    private long groupID;
    private SplitStrategy splitStrategy;
    private Map<Long, Double> splitMap;

    public Transaction(String description, long paidBy, double amount, long groupID, SplitStrategy splitStrategy, Map<Long, Double> splitMap) {
        this.description = description;
        this.paidBy = paidBy;
        this.amount = amount;
        this.groupID = groupID;
        this.splitStrategy = splitStrategy;
        this.splitMap = splitMap;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(long paidBy) {
        this.paidBy = paidBy;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getGroupID() {
        return groupID;
    }

    public void setGroupID(long groupID) {
        this.groupID = groupID;
    }

    public SplitStrategy getSplitStrategy() {
        return splitStrategy;
    }

    public void setSplitStrategy(SplitStrategy splitStrategy) {
        this.splitStrategy = splitStrategy;
    }

    public Map<Long, Double> getSplitMap() {
        return splitMap;
    }

    public void setSplitMap(Map<Long, Double> splitMap) {
        this.splitMap = splitMap;
    }
}
