package com.mendel.challenge.mapper;

import com.mendel.challenge.dto.TransactionDto;
import com.mendel.challenge.model.Transaction;

public class TransactionMapper {

    public static Transaction toModel(Long id, TransactionDto transactionDto) {
        Transaction transaction = new Transaction(
                id,
                transactionDto.amount(),
                transactionDto.type()
        );
        transaction.setParentId(transactionDto.parentId());
        return transaction;
    }

    public static TransactionDto toDto(Transaction transaction) {
        return new TransactionDto(
                transaction.getAmount(),
                transaction.getType(),
                transaction.getParentId()
        );
    }
}
