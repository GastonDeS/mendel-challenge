package com.mendel.challenge.service;

import com.mendel.challenge.dto.TransactionDto;
import com.mendel.challenge.mapper.TransactionMapper;
import com.mendel.challenge.model.Transaction;
import com.mendel.challenge.persistence.TransactionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionPersistence transactionPersistence;

    public Optional<TransactionDto> createTransaction(Long transactionId, TransactionDto transactionDto) {
        Transaction transaction = TransactionMapper.toModel(transactionId, transactionDto);
        Optional<Transaction> savedTransaction = transactionPersistence.createTransaction(transaction);
        return savedTransaction.map(TransactionMapper::toDto);
    }

    public List<Long> getTransactionsByType(final String type) {
        return transactionPersistence.getTransactionsByType(type);
    }

    public Double getTransitiveTransactionsAmount(final Long transactionId) {
        return transactionPersistence.getTransitiveTransactionsAmount(transactionId);
    }
}
