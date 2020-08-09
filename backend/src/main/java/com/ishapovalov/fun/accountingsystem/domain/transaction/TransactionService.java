package com.ishapovalov.fun.accountingsystem.domain.transaction;

import com.ishapovalov.fun.accountingsystem.domain.account.RegularAccount;
import com.ishapovalov.fun.accountingsystem.domain.transaction.model.Transaction;
import com.ishapovalov.fun.accountingsystem.domain.transaction.model.TransactionCompletionStatus;
import com.ishapovalov.fun.accountingsystem.domain.transaction.model.TransactionType;
import com.ishapovalov.fun.accountingsystem.domain.transaction.exceptions.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


public class TransactionService {
    public Map<UUID, Transaction> transactionHistory = new ConcurrentHashMap<>();

    public Transaction getTransaction(String id) {
        return transactionHistory.get(UUID.fromString(id));
    }

    public Collection<Transaction> getTransactionHistory() {
        return transactionHistory.values()
                .stream()
                .sorted((t1, t2) -> t2.getDateTime().compareTo(t1.getDateTime()))
                .collect(Collectors.toList());
    }

    public Transaction commitTransaction(
            RegularAccount account, double amount, String transactionTypeName
    ) {
        TransactionType transactionType = TransactionType.fromName(transactionTypeName);
        return commitTransaction(account, amount, transactionType);
    }

    public Transaction commitTransaction(
            RegularAccount account, double amount, TransactionType transactionType
    ) {
        if (Objects.isNull(transactionType)) {
            throw new UnknownTransactionTypeException();
        }
        Transaction transaction = new Transaction(
                account.getId(),
                amount,
                transactionType,
                LocalDateTime.now()
        );

        try {
            switch (transactionType) {
                case DEBIT: {
                    this.debit(account, amount);
                    break;
                }
                case CREDIT: {
                    this.credit(account, amount);
                    break;
                }
            }
            transaction.setStatus(TransactionCompletionStatus.SUCCESSFUL);
        } catch (InvalidOperationAmountException | InsufficientFundsException ex) {
            transaction.setStatus(TransactionCompletionStatus.FAILED);
            throw ex;
        } finally {
            transactionHistory.put(transaction.getUid(), transaction);
        }

        return transaction;
    }

    private void credit(RegularAccount account, double amount) {
        ensureAmountIsPositive(amount);

        synchronized (this) {
            // I know we may have overflow here.
            double newBalance = account.getBalance() + amount;
            account.setBalance(newBalance);
        }
    }

    private void debit(RegularAccount account, double amount) {
        ensureAmountIsPositive(amount);

        synchronized (this) {
            double balance = account.getBalance();
            if (balance < amount) {
                throw new InsufficientFundsException();
            }
            double newBalance = balance - amount;
            account.setBalance(newBalance);
        }
    }

    private void ensureAmountIsPositive(double amount) {
        if (amount < 0) {
            throw new InvalidOperationAmountException();
        }
    }
}
