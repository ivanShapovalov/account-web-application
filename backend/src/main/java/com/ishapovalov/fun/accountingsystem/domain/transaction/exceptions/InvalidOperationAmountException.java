package com.ishapovalov.fun.accountingsystem.domain.transaction.exceptions;

public final class InvalidOperationAmountException extends RuntimeException {
    public InvalidOperationAmountException() {
        super("Amount value is not valid");
    }
}
