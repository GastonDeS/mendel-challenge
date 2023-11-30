package com.mendel.challenge;

import com.mendel.challenge.model.Transaction;
import com.mendel.challenge.persistence.TransactionPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TransactionTest {

    @Autowired
    private TransactionPersistence transactionPersistence;

    @Test
    public void createTransactions() {
        Transaction transaction1 = new Transaction(1L, 100.0, "food");
        Transaction transaction2 = new Transaction(2L, 200.0, "shopping");
        Transaction transaction3 = new Transaction(3L, 300.0, "shopping");

        transactionPersistence.createTransaction(transaction1);
        transactionPersistence.createTransaction(transaction2);
        transactionPersistence.createTransaction(transaction3);

        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);
        assertEquals(transactions, transactionPersistence.getTransactions());
    }
}
