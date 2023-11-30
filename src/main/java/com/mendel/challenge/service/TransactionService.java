package com.mendel.challenge.service;

import com.mendel.challenge.dto.TransactionDto;
import com.mendel.challenge.mapper.TransactionMapper;
import com.mendel.challenge.model.Transaction;
import com.mendel.challenge.persistence.TransactionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TransactionService {

    @Autowired
    public TransactionPersistence transactionPersistence;

    public Optional<TransactionDto> createTransaction(Long transactionId, TransactionDto transactionDto) {
        Transaction transaction = TransactionMapper.toModel(transactionId, transactionDto);
        Optional<Transaction> savedTransaction = transactionPersistence.createTransaction(transaction);
        return savedTransaction.map(TransactionMapper::toDto);
    }

}