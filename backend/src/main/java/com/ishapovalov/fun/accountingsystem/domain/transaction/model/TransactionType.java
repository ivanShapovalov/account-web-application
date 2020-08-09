package com.ishapovalov.fun.accountingsystem.domain.transaction.model;

import com.ishapovalov.fun.accountingsystem.domain.transaction.exceptions.*;

public enum TransactionType {
    DEBIT, CREDIT;

    public static TransactionType fromName(String value) throws UnknownTransactionTypeException {
        for (TransactionType transactionType : TransactionType.values()) {
            if (transactionType.name().equalsIgnoreCase(value)) {
                return transactionType;
            }
        }

        throw new UnknownTransactionTypeException();
    }
}
