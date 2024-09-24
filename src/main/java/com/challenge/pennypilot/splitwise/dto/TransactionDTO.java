package com.challenge.pennypilot.splitwise.dto;

import com.challenge.pennypilot.splitwise.exception.InvalidDataProvidedException;
import com.challenge.pennypilot.splitwise.model.Group;
import com.challenge.pennypilot.splitwise.model.Transaction;
import org.springframework.util.StringUtils;

public class TransactionDTO extends AbstractDTO {
    private Double amount;
    private String description;
    private Long paidBy;
    private Long groupId;
    private long transactionId;

    public TransactionDTO() {}
    public TransactionDTO(Transaction transaction) {
        this.amount = transaction.getAmount();
        this.paidBy = transaction.getPaidBy();
        this.amount = transaction.getAmount();
        this.description = transaction.getDescription();
        Group group = transaction.getGroup();
        if (group != null) {
            this.groupId = group.getGroupId();
        }
        this.transactionId = transaction.getTransactionId();
    }

    @Override
    public void validate() throws InvalidDataProvidedException {
        if (!StringUtils.hasLength(description)) {
            throw new InvalidDataProvidedException("Description must not be empty");
        }
        if (paidBy == null || paidBy == 0) {
            throw new InvalidDataProvidedException("Enter who paid this transaction");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(Long paidBy) {
        this.paidBy = paidBy;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }
}
