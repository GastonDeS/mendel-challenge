package com.mendel.challenge;

import com.mendel.challenge.model.Transaction;
import com.mendel.challenge.persistence.TransactionPersistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TransactionTest {
    private final Transaction transaction1 = new Transaction(1L, 100.0, "food");
    private final Transaction transaction2 = new Transaction(2L, 200.0, "shopping");
    private final Transaction transaction3 = new Transaction(3L, 300.0, "shopping");

    @Autowired
    private TransactionPersistence transactionPersistence;

    @BeforeEach
    public void deleteTransactions() {
        transactionPersistence.deleteTransactions();
    }

    @Test
    public void createTransactions() {
        makeTransactions();

        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);
        assertEquals(transactions, transactionPersistence.getTransactions());
    }

    @Test
    public void createTransactionsWithParent() {
        makeTransactionsWithParent();

        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);
        assertEquals(transactions, transactionPersistence.getTransactions());
    }

    @Test
    public void createTransactionsWithParentNotFound() {
        Transaction transaction1 = new Transaction(1L, 100.0, "food");
        transaction1.setParentId(0L);

        assertThrows(IllegalArgumentException.class, () -> transactionPersistence.createTransaction(transaction1));
    }

    @Test
    public void getTransactionsByType() {
        makeTransactions();

        List<Long> transactionIds = Arrays.asList(2L, 3L);
        assertEquals(transactionIds, transactionPersistence.getTransactionsByType("shopping"));
    }

    @Test
    public void getEmptyTransactionsByType() {
        List<Long> emptyTransactionIds = new ArrayList<>();
        assertEquals(emptyTransactionIds, transactionPersistence.getTransactionsByType("food"));
    }

    @Test
    public void getFullTransitiveTransactionsAmount() {
        makeTransactionsWithParent();

        assertEquals(600.0, transactionPersistence.getTransitiveTransactionsAmount(1L));
    }

    @Test
    public void getNoChildTransitiveTransactionsAmount() {
        makeTransactionsWithParent();

        assertEquals(300.0, transactionPersistence.getTransitiveTransactionsAmount(3L));
    }

    @Test
    public void getPartialTransitiveTransactionsAmount() {
        makeTransactionsWithParent();

        assertEquals(500.0, transactionPersistence.getTransitiveTransactionsAmount(2L));
    }



    private void makeTransactions() {
        transactionPersistence.createTransaction(transaction1);
        transactionPersistence.createTransaction(transaction2);
        transactionPersistence.createTransaction(transaction3);
    }

    private void makeTransactionsWithParent() {
        transaction2.setParentId(1L);
        transaction3.setParentId(2L);

        makeTransactions();
    }
}
