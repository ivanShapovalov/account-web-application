package com.ishapovalov.fun.accountingsystem.resources.transaction;

import com.ishapovalov.fun.accountingsystem.domain.transaction.model.Transaction;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class TransactionDto {

    // This may have been configurable depending on locale.
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

    private final String id;
    private final double amount;
    private final String type;
    private final String date;
    private final String status;

    private TransactionDto(String id, double amount, String type, String date, String status) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.status = status;
    }

    public static TransactionDto fromTransaction(Transaction transaction) {
        return new TransactionDto(
                transaction.getUid().toString(),
                transaction.getAmount(),
                transaction.getType().name(),
                transaction.getDateTime().format(DATE_TIME_FORMAT),
                transaction.getStatus().name()
        );
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }
}
