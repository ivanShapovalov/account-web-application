package com.ishapovalov.fun.accountingsystem.domain.transaction.exceptions;

public class UnknownTransactionTypeException extends RuntimeException {
public UnknownTransactionTypeException() {
        super("Transaction type not supported");
    }
}
