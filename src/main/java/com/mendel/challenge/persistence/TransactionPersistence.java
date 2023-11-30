package com.mendel.challenge.persistence;

import com.mendel.challenge.model.Transaction;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Log4j2
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

    public Double getTransitiveTransactionsAmount(Long transactionId) {
        Transaction transaction = transactions.get(transactionId);
        if (transaction == null) {
            return 0.0;
        }
        Double amount = transaction.getAmount();
        for (Transaction childTransaction : transactions.values()) {
            if (childTransaction.getParentId() != null && childTransaction.getParentId().equals(transactionId)) {
                amount += getTransitiveTransactionsAmount(childTransaction.getId());
            }

        }
        return amount;
    }
}
