package com.mendel.challenge.persistence;

import com.mendel.challenge.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TransactionPersistence {

    private final Map<Long, Transaction> transactions = new HashMap<>();

    public Optional<Transaction> createTransaction(Transaction transaction) {
        if (transaction.getParentId() != null && !transactions.containsKey(transaction.getParentId())) {
            throw new IllegalArgumentException("Parent transaction not found");
        }
        transactions.put(transaction.getId(), transaction);
        return Optional.of(transaction);
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions.values());
    }

    public List<Long> getTransactionsByType(String type) {
        List<Long> transactionIds = new ArrayList<>();
        for (Transaction transaction : transactions.values()) {
            if (transaction.getType().equals(type)) {
                transactionIds.add(transaction.getId());
            }
        }
        return transactionIds;
    }
}
