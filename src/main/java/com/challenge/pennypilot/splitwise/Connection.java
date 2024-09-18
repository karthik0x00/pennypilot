package com.challenge.pennypilot.splitwise;

public class Connection {
    private long toUserID;
    private double amount;

    public Connection(long toUserID, double amount) {
        this.toUserID = toUserID;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public long getToUserID() {
        return toUserID;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
