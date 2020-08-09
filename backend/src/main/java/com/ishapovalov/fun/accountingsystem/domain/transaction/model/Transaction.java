package com.ishapovalov.fun.accountingsystem.domain.transaction.model;

import java.time.LocalDateTime;
import java.util.UUID;

public final class Transaction {

    private final UUID uid;
    private final UUID accountId;
    private final double amount;
    private final TransactionType type;
    private final LocalDateTime dateTime;
    private TransactionCompletionStatus status;

    public Transaction(
            UUID accountId,
            double amount,
            TransactionType type,
            LocalDateTime dateTime
    ) {
        this.uid = UUID.randomUUID();
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.dateTime = dateTime;
    }

    public UUID getUid() {
        return uid;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setStatus(TransactionCompletionStatus status) {
        this.status = status;
    }

    public TransactionCompletionStatus getStatus() {
        return status;
    }
}
