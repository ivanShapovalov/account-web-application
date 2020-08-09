package com.ishapovalov.fun.accountingsystem.domain.account;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.UUID;

/*
 * NOTE: I would imagine a class hierarchy for accounts of different types, holding currency information etc.
 */
@NotThreadSafe
public final class RegularAccount {

    // NOTE! Double is used for simplicity sake here.
    // In real world we'd better use BigDecimal with scale, precision, and other goodies.
    private final UUID id = UUID.randomUUID();
    private double balance = 0;

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public UUID getId() {
        return id;
    }
}
